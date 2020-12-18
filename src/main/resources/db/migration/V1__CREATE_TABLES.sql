create table authorities (
    authority_id serial primary key,
    permission varchar(30)
);

create table accounts (
    account_id serial primary key,
    username varchar(30),
    encoded_password varchar(100)
);

create table accounts_authorities (
    account_id int references accounts (account_id),
	authority_id int references authorities (authority_id),
	primary key (account_id, authority_id)
);

create table doctors (
    doctor_id serial primary key,
    age integer,
    gender varchar(10),
    first_name varchar(100),
    last_name varchar(100),
    description varchar(400),
    account_id int references accounts (account_id)
);

create table patients(
    patient_id serial primary key,
    age integer,
    gender varchar(10),
    first_name varchar(100),
    last_name varchar(100),
    account_id int references accounts (account_id)
);

create table languages (
    language_id serial primary key,
    language_name varchar(50)
);

create table specializations (
    specialization_id serial primary key,
    specialization_name varchar(100)
);

create table appointments (
    appointment_id serial primary key,
    appointment_date date,
    start_time time,
    end_time time,
    notes varchar(300),
    is_cancelled boolean,
    patient_id int references patients (patient_id),
    doctor_id int references doctors (doctor_id)
);

create table doctors_languages (
    doctor_id int references doctors (doctor_id),
    language_id int references languages (language_id),
    primary key (doctor_id, language_id)
);

create table doctors_specializations (
    doctor_id int references doctors (doctor_id),
    specialization_id int references specializations (specialization_id),
    primary key (doctor_id, specialization_id)
);