SELECT car.name, car_body.descript, engine.descript, transmission.descript
FROM car, car_body, engine, transmission
WHERE car_body.id = car.car_body_id
AND engine.id = car.engine_id
AND transmission.id = car.transmission_id;

SELECT descript FROM car
RIGHT OUTER JOIN car_body ON car_body.id = car.car_body_id
WHERE car.id IS NULL;

SELECT descript FROM car
RIGHT OUTER JOIN engine ON engine.id = car.engine_id
WHERE car.id IS NULL;

SELECT descript FROM car
RIGHT OUTER JOIN transmission ON transmission.id = car.transmission_id
WHERE car.id IS NULL;