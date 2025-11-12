

CREATE DATABASE fittrack;
\c fittrack;

-- Tabela de usuários
CREATE TABLE usuario (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    idade INT,
    peso_kg DECIMAL(5,2),
    altura_m DECIMAL(4,2),
    sexo VARCHAR(10)
);

-- Tabela de tipos de atividade
CREATE TABLE tipo_atividade (
    id UUID PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL
);

-- Tabela de atividades registradas
CREATE TABLE atividade (
    id UUID PRIMARY KEY,
    usuario_id UUID REFERENCES usuario(id) ON DELETE CASCADE,
    tipo_atividade_id UUID REFERENCES tipo_atividade(id),
    data TIMESTAMP NOT NULL,
    duracao_minutos DECIMAL(6,2),
    distancia_km DECIMAL(6,2),
    intensidade VARCHAR(20),
    calorias_estimadas DECIMAL(6,2)
);

-- Tabela de metas
CREATE TABLE meta (
    id UUID PRIMARY KEY,
    usuario_id UUID REFERENCES usuario(id) ON DELETE CASCADE,
    tipo_atividade_id UUID REFERENCES tipo_atividade(id),
    tipo_meta VARCHAR(20) NOT NULL,
    valor_meta DECIMAL(8,2) NOT NULL,
    recorrencia VARCHAR(20) NOT NULL,
    periodo_inicio DATE NOT NULL,
    periodo_fim DATE NOT NULL,
    concluida BOOLEAN DEFAULT FALSE
);
