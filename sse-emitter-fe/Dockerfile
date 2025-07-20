# Build Angular app
FROM node:18 AS build

# Required for Node 18+ to avoid OpenSSL crypto issue
ENV NODE_OPTIONS=--openssl-legacy-provider

WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Serve Angular app
FROM nginx:alpine
COPY --from=0 /app/dist/sse-emitter-fe /usr/share/nginx/html


