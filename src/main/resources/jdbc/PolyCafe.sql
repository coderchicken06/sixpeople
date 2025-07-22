-- Tạo CSDL
CREATE DATABASE polycafe;
GO
-- Sử dụng CSDL vừa tạo
USE polycafe;
GO

-- Tạo bảng Categories
CREATE TABLE Categories(
    Id NVARCHAR(20) NOT NULL,
    Name NVARCHAR(50) NOT NULL,
    PRIMARY KEY(Id)
);
GO

-- Tạo bảng Drinks
CREATE TABLE Drinks(
    Id NVARCHAR(20) NOT NULL,
    Name NVARCHAR(50) NOT NULL,
    UnitPrice FLOAT NOT NULL,
    Discount FLOAT NOT NULL,
    Image NVARCHAR(50) NOT NULL,
    Available BIT NOT NULL,
    CategoryId NVARCHAR(20) NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(CategoryId) REFERENCES Categories(Id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);
GO

-- Tạo bảng Cards
CREATE TABLE Cards(
    Id INT NOT NULL,
    Status INT NOT NULL,
    PRIMARY KEY(Id)
);
GO

-- Tạo bảng Users
CREATE TABLE Users(
    Username NVARCHAR(20) NOT NULL,
    Password NVARCHAR(50) NOT NULL,
    Enabled BIT NOT NULL,
    Fullname NVARCHAR(50) NOT NULL,
    Photo NVARCHAR(50) NOT NULL,
    Manager BIT NOT NULL,
    PRIMARY KEY(Username)
);
GO

-- Tạo bảng Bills
CREATE TABLE Bills(
    Id BIGINT NOT NULL IDENTITY(10000, 1),
    Username NVARCHAR(20) NOT NULL,
    CardId INT NOT NULL,
    Checkin DATETIME NOT NULL,
    Checkout DATETIME NULL,
    Status INT NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(Username) REFERENCES Users(Username) 
        ON UPDATE CASCADE,
    FOREIGN KEY(CardId) REFERENCES Cards(Id) 
        ON UPDATE CASCADE
);
GO

-- Tạo bảng BillDetails
CREATE TABLE BillDetails(
    Id BIGINT NOT NULL IDENTITY(100000, 1),
    BillId BIGINT NOT NULL,
    DrinkId NVARCHAR(20) NOT NULL,
    UnitPrice FLOAT NOT NULL,
    Discount FLOAT NOT NULL,
    Quantity INT NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(BillId) REFERENCES Bills(Id) 
        ON DELETE CASCADE,
    FOREIGN KEY(DrinkId) REFERENCES Drinks(Id) 
        ON UPDATE CASCADE
);
GO

-- Insert vào Categories
INSERT INTO Categories (Id, Name) VALUES
('CAT001', 'Coffee'),
('CAT002', 'Tea'),
('CAT003', 'Juice'),
('CAT004', 'Smoothie'),
('CAT005', 'Soda');
GO

-- Insert vào Drinks
INSERT INTO Drinks (Id, Name, UnitPrice, Discount, Image, Available, CategoryId) VALUES
('D001', 'Espresso', 2.5, 0.0, 'espresso.png', 1, 'CAT001'),
('D002', 'Green Tea', 2.0, 0.1, 'greentea.png', 1, 'CAT002'),
('D003', 'Orange Juice', 3.0, 0.0, 'orangejuice.png', 1, 'CAT003'),
('D004', 'Mango Smoothie', 4.0, 0.2, 'mangosmoothie.png', 0, 'CAT004'),
('D005', 'Cola', 1.5, 0.0, 'cola.png', 1, 'CAT005');
GO

-- Insert vào Cards
INSERT INTO Cards (Id, Status) VALUES
(101, 1),
(102, 0),
(103, 1),
(104, 2),
(105, 1);
GO

-- Insert vào Users (đổi Username bị trùng)
INSERT INTO Users (Username, Password, Enabled, Fullname, Photo, Manager) VALUES
('danh', '1', 1, 'John Doe', 'trump.png', 0),
('user2', 'password2', 1, 'Jane Smith', 'trump.png', 0),
('manager1', 'admin123', 1, 'Alice Brown', 'trump.png', 1),
('user3', 'password3', 0, 'Bob Johnson', 'trump.png', 0),
('user4', 'password4', 1, 'Emma Wilson', 'trump.png', 0),
('user5@gmail.com', '123', 1, 'Nguyễn Văn Tèo', 'trump.png', 1); 
go

-- Insert vào Bills
INSERT INTO Bills (Username, CardId, Checkin, Checkout, Status) VALUES
('user1', 101, '2025-05-21 08:00:00', '2025-05-21 09:00:00', 1),
('user2', 103, '2025-05-21 09:00:00', NULL, 0),
('manager1', 105, '2025-05-21 10:00:00', '2025-05-21 11:00:00', 1),
('user4', 101, '2025-05-21 11:30:00', NULL, 0),
('user1', 103, '2025-05-21 12:00:00', '2025-05-21 13:00:00', 1);
GO

-- Insert vào BillDetails
INSERT INTO BillDetails (BillId, DrinkId, UnitPrice, Discount, Quantity) VALUES
(10000, 'D001', 2.5, 0.0, 2),
(10000, 'D002', 2.0, 0.1, 1),
(10001, 'D003', 3.0, 0.0, 1),
(10002, 'D005', 1.5, 0.0, 3),
(10003, 'D001', 2.5, 0.0, 1);
GO
INSERT INTO Bills (Username, CardId, Checkin, Checkout, Status)
VALUES 
('manager1', 105, '2025-06-01 08:00:00', '2025-06-01 09:00:00', 1); -- BillId sẽ là 10005 nếu đúng IDENTITY

-- Thêm dữ liệu chi tiết cho 5 loại khác nhau
INSERT INTO BillDetails (BillId, DrinkId, UnitPrice, Discount, Quantity) VALUES
-- Coffee
(10005, 'D001', 400, 0.0, 40),
(10005, 'D001', 300, 0.0, 34),

-- Soda
(10005, 'D005', 905.9, 0.0, 16),

-- Smoothie
(10005, 'D004', 780.9, 0.0, 7),
(10005, 'D004', 600.9, 0.0, 10),

-- Juice
(10005, 'D003', 225.2, 0.0, 4),
(10005, 'D003', 27.0, 0.0, 4),

-- Tea
(10005, 'D002', 162.4, 0.0, 2);

SELECT 
    c.Name AS Loai,
    FORMAT(SUM((bd.UnitPrice - bd.UnitPrice * bd.Discount) * bd.Quantity), 'C', 'en-us') AS DoanhThu,
    SUM(bd.Quantity) AS SoLuong,
    FORMAT(MIN(bd.UnitPrice), 'C', 'en-us') AS GiaThapNhat,
    FORMAT(MAX(bd.UnitPrice), 'C', 'en-us') AS GiaCaoNhat,
    FORMAT(AVG(bd.UnitPrice), 'C', 'en-us') AS GiaTrungBinh
FROM 
    BillDetails bd
JOIN 
    Drinks d ON bd.DrinkId = d.Id
JOIN 
    Categories c ON d.CategoryId = c.Id
JOIN 
    Bills b ON bd.BillId = b.Id
WHERE 
    b.Checkout IS NOT NULL -- Chỉ tính hóa đơn đã thanh toán
GROUP BY 
    c.Name
ORDER BY 
    DoanhThu DESC;
	-- Hóa đơn mới (đã Checkout)

