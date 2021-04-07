SELECT CONCAT('DROP TABLE ', TABLE_NAME, ';')
FROM INFORMATION_SCHEMA.tables
WHERE TABLE_SCHEMA = 'zion';

SET foreign_key_checks = 0;

DROP TABLE characters;
DROP TABLE collected_nodes;
DROP TABLE conditions;
DROP TABLE conditions_node_types;
DROP TABLE hibernate_sequence;
DROP TABLE inventories;
DROP TABLE items;
DROP TABLE key_values;
DROP TABLE node_types;
DROP TABLE node_types_conditions;
DROP TABLE node_types_nodes;
DROP TABLE node_types_rewards;
DROP TABLE nodes;
DROP TABLE osm_matcher;
DROP TABLE osm_matcher_node_type;
DROP TABLE privileges;
DROP TABLE rewards;
DROP TABLE roles;
DROP TABLE roles_privileges;
DROP TABLE target_systems;
DROP TABLE tiles;
DROP TABLE users;
DROP TABLE users_roles;

SET foreign_key_checks = 1;