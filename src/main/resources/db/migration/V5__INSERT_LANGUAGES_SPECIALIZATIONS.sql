insert into languages (language_name) values
('Mandarin Chinese'),
('Spanish'),
('English'),
('Hindi'),
('Bengali'),
('Portuguese'),
('Russian'),
('Japanese'),
('Korean'),
('French'),
('German'),
('Vietnamese'),
('Italian');

insert into specializations (specialization_name) values
('Addiction medicine'),
('Anaesthesia'),
('Dermatology'),
('Emergency medicine'),
('General practice'),
('Intensive care medicine'),
('Medical administration'),
('Obstetrics and gynaecology'),
('Occupational and environmental medicine'),
('Ophthalmology'),
('Paediatrics and child health'),
('Pain medicine'),
('Palliative medicine'),
('Pathology'),
('Physician'),
('Psychiatry'),
('Public health medicine'),
('Radiation oncology'),
('Radiology'),
('Rehabilitation medicine'),
('Sexual health medicine'),
('Sport and exercise medicine'),
('Surgery');

insert into doctors_languages (doctor_id, language_id) values
(1,1),
(1,2),
(1,4),
(2,6),
(2,5),
(3,7),
(4,9),
(5,3),
(5,11),
(6,12),
(6,10),
(6,8),
(7,7),
(8,5),
(8,4),
(9,3),
(10,8),
(11,4),
(12,5),
(13,8),
(14,9),
(15,12),
(15,1),
(16,3),
(17,5),
(18,1),
(18,10),
(19,11),
(19,7),
(20,4),
(21,5),
(22,6),
(23,2),
(24,11),
(25,4),
(25,7),
(26,5),
(27,8),
(28,7),
(29,9),
(29,1),
(30,1),
(31,5),
(32,4),
(33,2),
(34,1),
(34,6),
(35,2),
(36,2),
(37,2),
(38,2),
(39,2);

insert into doctors_specializations (doctor_id, specialization_id) values
(2,1),
(1,22),
(1,14),
(2,12),
(2,18),
(3,4),
(4,5),
(5,8),
(5,7),
(6,6),
(6,9),
(6,3),
(7,13),
(8,16),
(8,19),
(9,21),
(10,20),
(11,15),
(12,1),
(13,17),
(14,18),
(15,7),
(15,5),
(16,14),
(17,3),
(18,6),
(18,16),
(19,18),
(19,22),
(20,21),
(21,1),
(22,1),
(23,4),
(24,7),
(25,1),
(25,8),
(26,10),
(27,12),
(28,16),
(29,1),
(29,2),
(30,4),
(31,9),
(32,19),
(33,18),
(34,20),
(34,21),
(34,16),
(35,13),
(36,12),
(37,1),
(38,10),
(39,17);






