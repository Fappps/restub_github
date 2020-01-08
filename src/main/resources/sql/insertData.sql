-- insert initial test data
-- the id is hardcode to enable references between further test data
INSERT INTO horse (ID, NAME, BREED, MIN_SPEED, MAX_SPEED, CREATED, UPDATED )
VALUES (1, 'Joe', 'Cob', 40.1, 50.0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  (2, 'Lisa', 'Arab', 40.5, 50.7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  (3, 'Jim', 'Andalusian', 40.0, 60.0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- insert initial test data
-- the id is hardcode to enable references between further test data
INSERT INTO jockey (ID, NAME, SKILLS, CREATED, UPDATED )
VALUES (1, 'Joe', 30.2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  (2, 'Slu', 35.3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  (3, 'TyHoe', 98.3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


-- insert initial test data
-- the id is hardcode to enable references between further test data
INSERT INTO simulation (ID, NAME, CREATED)
VALUES (1, 'Simulation1', CURRENT_TIMESTAMP()),
  (2, 'Simulation2', CURRENT_TIMESTAMP()),
  (3, 'Simulation3', CURRENT_TIMESTAMP());

-- insert initial test data
-- the id is hardcode to enable references between further test data
INSERT INTO simulation_jockey_horse (id,horseID, jockeyID, simulationID,rank, luckfactor, avgSpeed, horseSpeed)
VALUES (1,1, 1, 1,1, 1.05, 69.69, 53.33),
 (2,2, 2, 1,2, 1.08, 72.32,53.33);