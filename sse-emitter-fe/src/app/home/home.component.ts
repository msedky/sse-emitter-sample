import { Component, NgZone, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  buildingCount: number;
  sessionId = Date.now().toString();
  eventSource: EventSource;

  constructor(private http: HttpClient, private router: Router, private zone: NgZone) { }

  ngOnInit(): void {
    this.loadBuildingCount();
    this.subscribe();
  }

  subscribe() {
    this.eventSource = new EventSource(`${environment.baseUrl}/api/v1/sse/subscribe/${this.sessionId}`);
    this.eventSource.onmessage = (event) => {
      console.log('Received SSE message:', event.data);
      this.zone.run(() => {
        this.loadBuildingCount();
      });
    };
  }

  loadBuildingCount() {
    this.http.get<number>(`${environment.baseUrl}/api/v1/buildings/count`)
      .subscribe(count => {
        console.log('Updated count:', count);
        this.buildingCount = count;
      });
  }

  logout() {
    this.http.delete(`${environment.baseUrl}/api/v1/sse/unsubscribe/${this.sessionId}`).subscribe(() => {
      this.eventSource.close();
      this.router.navigate(['/login']);
    });
  }
}
