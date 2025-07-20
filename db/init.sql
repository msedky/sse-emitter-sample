CREATE TABLE IF NOT EXISTS buildings (
    id UUID PRIMARY KEY,
    name_en VARCHAR(255),
    name_ar VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO buildings (id, name_en, name_ar) VALUES
  (gen_random_uuid(), 'Tower One', 'البرج الأول'),
  (gen_random_uuid(), 'Tower Two', 'البرج الثاني'),
  (gen_random_uuid(), 'Tower Three', 'البرج الثالث');