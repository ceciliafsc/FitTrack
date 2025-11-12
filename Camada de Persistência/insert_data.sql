

-- Inserindo tipos de atividade padrão
INSERT INTO tipo_atividade (id, nome) VALUES
(gen_random_uuid(), 'Caminhada'),
(gen_random_uuid(), 'Corrida'),
(gen_random_uuid(), 'Ciclismo'),
(gen_random_uuid(), 'Musculação');

-- Usuário de exemplo
INSERT INTO usuario (id, nome, email, senha_hash, idade, peso_kg, altura_m, sexo)
VALUES (gen_random_uuid(), 'Cecília Fernandes', 'cecilia@fittrack.com', '12345678hash', 22, 60.5, 1.68, 'Feminino');
