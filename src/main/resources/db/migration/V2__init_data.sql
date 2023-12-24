INSERT INTO roles (id, name, name_localization) VALUES
('4b83af3d-c1ca-4a3e-8996-3fbca0110eae', 'USER', 'Пользователь'),
('93460068-1c30-4f10-bb4b-0e81a16cfb1b', 'ADMIN', 'Администратор');

INSERT INTO users (id, activated, avatar, birthday, email, fio, password, phone, role_id) VALUES
('67d760e6-bfc1-42b5-83df-ef881f65f3bd', true, '', '2023-12-25', 'admin@admin.com', 'Админов Админ Админович', 'password', '+79991323432', '93460068-1c30-4f10-bb4b-0e81a16cfb1b');
