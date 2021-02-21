create database QLGIAY;
use QLGIAY;
-- tạo bảng
create table account(
                        id int auto_increment primary key ,
                        username nvarchar(255) unique,
                        password nvarchar(255),
                        role int
);
insert into account(username, password, role) VALUES (N'Vũ Văn Mừng','123',1);
insert into account(username, password, role) VALUES (N'Vũ Đức Thành','123',1);
insert into account(username, password, role) VALUES (N'Vũ Đan Linh','123456',2);
insert into account(username, password, role) VALUES (N'Vũ Linh','123456',2);
insert into account(username, password, role) VALUES (N'Hoàng Cúc','123123',3);
insert into account(username, password, role) VALUES (N'Đỗ Thanh','abc',3);
insert into account(username, password, role) VALUES (N'Nguyễn Kim','abcabc',3);
insert into account(username, password, role) VALUES (N'Hoàng Lan','123123',3);
insert into account(username, password, role) VALUES (N'Trần Đào','121212',3);
insert into account(username, password, role) VALUES (N'Vương Thừa','121',3);

create table category(
                         id int auto_increment primary key ,
                         name nvarchar(255)
);
insert into category(name) values ('Nike');
insert into category(name) values ('Adidas');
insert into category(name) values ('Puma');
insert into category(name) values ('Bata');

create table product(
                        id int auto_increment primary key ,
                        name nvarchar(255),
                        image text,
                        description text,
                        price double,
                        id_category int,
                        id_account int,
                        foreign key (id_category) references category(id),
                        foreign key (id_account) references account(id)
);
insert into product(name, image, description, price, id_category, id_account)
VALUES ('Nike Air Max',
        'https://kiza.vn/blog/wp-content/uploads/2018/01/big-2-.jpg',
        'Bứt phá hơn hẳn các mẫu nike trước đó ở khoản thiết kế nên ngẫu nhiên chúng rất được lòng các bạn nữ. Thay vì những đôi giày thể thao Nike thông thường với phần đế bằng cao su mềm. Thì em này vượt trội hơn khi được nhà sản xuất ưu ái sử dụng thêm các túi khí ở đế giày. Thêm vào đó gót giày và hai bên hông, được thiết kế sau lớp cao su trong.',
        2000000,
        1,
        3
       );
insert into product(name, image, description, price, id_category, id_account)
VALUES ('Nike Zoom',
        'https://kiza.vn/blog/wp-content/uploads/2018/01/giay-nike-zoom-pegasus-33-hong-nhat-nzp20-1_grande.jpg',
        'Thiết kế đơn giản được phối lưới ở phía sau, phần mũi giày được cải tiến để tạo cho form giày luôn chuẩn đẹp mà không mất đi sự êm ái khi sử dụng. Đó chính xác là điểm cộng khiến em ý vượt mặt các đối thủ. Vốn dĩ thiết kế của Nike đã cực kỳ êm ái lại luôn được áp dụng những công nghệ mới nhất vào sản phẩm nên đã giành trọn trái tim của các tín đồ thời trang.',
        1450000,
        1,
        4
       );
insert into product(name, image, description, price, id_category, id_account)
VALUES ('Nike Flyknit',
        'https://kiza.vn/blog/wp-content/uploads/2018/08/mua-giay-Nike-Flyknit-cho-nam.jpg',
        'Dòng giày Nike này được coi là chiến binh hùng mạnh nhất của nhà Nike. Nike Flyknit tích hợp hàng loạt công nghệ cao, tiên tiến hơn hẳn các mẫu giày trước đó. Công nghệ này giúp đôi giày ôm khít bàn chân tạo cảm giác tối đa khi sử dụng mà lại cực kỳ thoáng khí.',
        3570000,
        1,
        5
       );
insert into product(name, image, description, price, id_category, id_account)
VALUES ('Nike SF-AF1',
        'https://kiza.vn/blog/wp-content/uploads/2018/08/nike-sf-af1-desert-camo-where-to-buy.jpg',
        'Dưới bàn tay của Ben Kirschner, phiên bản lần này mang hoàn toàn hơi hướng quân quân đội giống hệt như tên gọi của nó. Vẫn là những chi tiết giống những thế hệ đàn anh. Nhưng lần này phiên bản Desert Camo lại thêm 2 dây buộc quanh cổ cùng màu sắc không thể bắt mắt hơn. Đây cũng chính là lý do item này chễm chệ ở vị trí đầu bảng',
        6000000,
        1,
        5
       );
insert into product(name, image, description, price, id_category, id_account)
VALUES ('Adidas Alphabounce',
        'https://giaygiare.vn/upload/images/top-5-dong-giay-adidas-duoc-ua-chuong-nhat-nam-2019-7.jpg',
        'Adidas Alphabounce có 2 phiên bản là Beyond 2017 & Beyond 2018 đã gặt hái được rất nhiều thành công & luôn đạt top trong những dòng giày được yêu thích nhất đối với giới trẻ Việt Nam & quốc tế.Ngoài ra cũng phải kể đến phiên bản Alphabounce Instinct với kiểu dáng vô cùng mới lạ & độc đáo. Nếu như phần upper của phiên bản Beyond là kiểu kết hợp nhiều mảnh vải ghép thì phần upper của giày Instinct được tạo nên từ một tấm vải duy nhất trông rất mềm mại, với các đường vân sóng bao phủ thân giày một cách tinh tế và đẹp mắt.',
        2500000,
        2,
        4
       );
insert into product(name, image, description, price, id_category, id_account)
VALUES ('Adidas Yeezy',
        'https://giaygiare.vn/upload/images/adidas-yeezy-350-v2-cloud-white-nam-nu-1-1-7.jpg',
        'Nói đến thương hiệu Yeezy thì hẳn các fan trung thành của Adidas đều đã nghe qua rồi phải không ạ? Vì bằng chứng là trong những năm gần đây giày Yeezy luôn đứng đầu về doanh số bán hàng của Adidas khi luôn đạt số lượng bán ra sản phẩm cực khủng so với các đối thủ khác.',
        2750000,
        2,
        3
       );
insert into product(name, image, description, price, id_category, id_account)
VALUES ('Adidas Prophere',
        'https://giaygiare.vn/upload/images/adidas-prophere-xanh-ngoc-nu-5.jpg',
        'Giày Adidas prophere là một trong những mẫu giày làm điên đảo giới trẻ không chỉ là nam giới mà còn cả nữ giới. Với thiết kế ấn tượng, màu sắc trẻ trung, năng động, chất lượng cao cấp bởi sản xuất bằng các công nghệ hiện đại & tân tiến nhất. Chính vì như thế mà mẫu giày prophere khiến các sneakerhead, fashionista đều phải chết mê chết mệt trong thời gian vừa qua.',
        1750000,
        2,
        10
       );
insert into product(name, image, description, price, id_category, id_account)
VALUES ('Puma Rs-x3',
        'https://quatasy.com/wp-content/uploads/2020/09/Gi%C3%A0y-Puma-RS-X3-Plas_Tech.jpg',
        'Nằm trong xu hướng giày sneaker 2020, cái tên Puma RS-X3 Puzzle là một trong những sản phẩm mới nhất thuộc dòng RS nổi tiếng của nhà Puma. Đôi giày được thiết kế với kiểu dáng gồ ghề cùng khả năng sáng tạo màu sắc các lớp TPU vô cùng nổi bật.',
        1200000,
        3,
        9
       );
insert into product(name, image, description, price, id_category, id_account)
VALUES ('Puma Skye Stripes',
        'https://quatasy.com/wp-content/uploads/2020/11/Gi%C3%A0y-Puma-Skye-Stripes.jpg',
        'Sản phẩm tiếp theo thuộc phong cách cổ điển đường phố mà Quatasy tin chắc sẽ tạo ấn tượng trong năm 2020 đó là Puma Skye Stripes.Với phần thân trên được làm bằng da đặc trưng . Đế cao su chống trượt cùng lớp đệm SoftFoam + sockliner êm ái.Nét độc đáo ở Puma Skye Stripes là khả năng phối màu đầy sáng tạo đã tạo nên phiên bản sneaker hoàn hảo dành cho phong cách thời trang của bạn.',
        2500000,
        3,
        6
       );
insert into product(name, image, description, price, id_category, id_account)
VALUES (' Puma RS-X Super',
        'https://quatasy.com/wp-content/uploads/2020/03/Gi%C3%A0y-Puma-RS-X-Super-Red.jpg',
        'Tiếp tục kế thừa công nghệ RS-X vô cùng thành công ở những phiên bản trước, một phiên bản mới tiếp theo đã được nhà mốt Puma gửi tới giới mô điệu trên toàn thế giới đó là Puma RS-X Super.Đôi giày được thiết kế với kiểu dáng phóng đại cùng khả năng sáng tạo màu sắc các lớp TPU đầy nét nổi bật. Phần upper được bằng làm lưới với công nghệ đàn hồi cao kết hợp với lớp đệm PU tạo nên sự thoải mái tối đa. Phần đế bằng cao su cung cấp lực kéo và độ bám kết hợp công chạy Retro tạo cảm giác linh hoạt khi di chuyển.',
        1500000,
        3,
        8
       );
insert into product(name, image, description, price, id_category, id_account)
VALUES ('Puma Carina',
        'https://quatasy.com/wp-content/uploads/2020/09/Gi%C3%A0y-Puma-Carina-Iridescent.jpg',
        'Được thiết kế lấy cảm hứng từ các thị trấn bãi biển của California những năm 80. Phần trên được làm bằng da cùng chi tiết PUMA Formstrip 2 bên đặc trưng tạo điểm nhấn.Ngoài ra, khả năng sáng tạo màu sắc nhẹ nhàng cùng chi tiết đế nâng cao đã tạo nên mẫu sneaker hoàn hảo đậm nét cá tính.',
        2500000,
        3,
        7
       );
insert into product(name, image, description, price, id_category, id_account)
VALUES ('Puma DEVA Suede',
        'https://quatasy.com/wp-content/uploads/2020/03/Gi%C3%A0y-Puma-DEVA-Suede.jpg',
        'Puma DEVA Suede là một trong những cái tên mới thuộc dòng Suede của nhà Puma. Đôi giày được thiết kế với kiểu dáng cổ điển đường phố điểm thêm phần đế gồ ghề, hầm hố tạo sự điểm nhấn.Tương tự như những thế hệ trước, phần thân trên vẫn được làm bằng lớp da lộn đặc trưng. Chi tiết Puma Formstrip ở 2 bên. Điểm nhấn độc đáo với phần đế giày cao cùng đường viền Puma đặc trưng đã tạo nên phong cách nổi bât thu hút mọi ánh nhìn',
        5500000,
        3,
        5
       );

create table orders(
                       id int auto_increment primary key ,
                       id_account int,
                       status int default 0,
                       date date,
                       foreign key (id_account) references account(id)
);
insert into orders(id_account, status, date) values (5,1,'2021/01/29');
insert into orders(id_account, status, date) values (6,2,'2021/01/09');
insert into orders(id_account, status, date) values (7,2,'2021/02/17');
insert into orders(id_account, status, date) values (8,1,'2021/01/19');
insert into orders(id_account, status, date) values (10,1,'2021/01/11');
insert into orders(id_account, status, date) values (9,2,'2021/01/15');
insert into orders(id_account, status, date) values (5,2,'2021/02/19');

create table orderDetail(
                            id int auto_increment primary key,
                            id_order int,
                            id_product int,
                            amount int not null,
                            foreign key (id_product) references product(id),
                            foreign key (id_order) references orders(id)
);
insert into orderDetail(id_order, id_product, amount) VALUES (1,1,2);
insert into orderDetail(id_order, id_product, amount) VALUES (1,8,2);
insert into orderDetail(id_order, id_product, amount) VALUES (2,2,2);
insert into orderDetail(id_order, id_product, amount) VALUES (3,4,2);
insert into orderDetail(id_order, id_product, amount) VALUES (7,5,1);
insert into orderDetail(id_order, id_product, amount) VALUES (7,3,1);
insert into orderDetail(id_order, id_product, amount) VALUES (6,11,1);
insert into orderDetail(id_order, id_product, amount) VALUES (6,12,1);
insert into orderDetail(id_order, id_product, amount) VALUES (5,7,1);
insert into orderDetail(id_order, id_product, amount) VALUES (5,4,1);
insert into orderDetail(id_order, id_product, amount) VALUES (4,9,1);
select * from product;
select * from product order by id desc limit 1;
select * from product order by id desc limit 3;

