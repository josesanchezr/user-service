-- Crear la tabla de usuarios
CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    token VARCHAR(255),
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

-- Crear la tabla de teléfonos
CREATE TABLE phones (
    id UUID PRIMARY KEY,
    number VARCHAR(20) NOT NULL,
    citycode VARCHAR(10) NOT NULL,
    countrycode VARCHAR(10) NOT NULL,
    user_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Opcional: Crear índices para mejorar el rendimiento de búsqueda
CREATE INDEX idx_user_email ON users (email);
CREATE INDEX idx_phone_user_id ON phones (user_id);
