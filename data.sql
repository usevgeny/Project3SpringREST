create table sensor(
    sensor_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar NOT NULL
    );

create table measurement(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "value" real NOT NULL,
    raining boolean NOT NULL,
    sensor_id int NOT NULL REFERENCES sensor(sensor_id)
    );

insert into sensor(name) values('testSensor');



alter table measurement add column register_time timestamp;