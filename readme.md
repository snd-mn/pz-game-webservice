<h1>pz-game-webservice</h1>

This is the backend for a mobile geo-caching-game.<br />
It hold users, geo-caches and additional data for the game-play-elements.<br />
It generates game-related-data from open-street-map-services (Overpass-Turbo).<br />

<h2>Prerequisites</h2>
You need to specify a Overpass-Turbo-Service for this <br />
the url for the OTS can be definded in the application.properties<br />
<code>pz.overpass.turbo.interpreterUrl=http://localhost/api/interpreter</code><br />
hosting the overpass-turbo + osm is defenetly recommended (osm is a bit restrictive in the number of api calls you are allowed to), <br />
you can find a docker for hosting here https://github.com/officer-merge/docker-overpass-api <br />
in case you wanna build it your own, you can use this repo: https://github.com/officer-merge/Overpass-API <br />

<h2>Custom-Configuration</h2>
this component is just the backend.
its configurable so you can define your own which game-nodes are created from overpass-nodes.
for further info, take a look into these Entities:<br />
<code>src/main/java/org/projectzion/game/persitence/entities/OsmMatcher.java<br />
src/main/java/org/projectzion/game/persitence/entities/OsmMatcherNodeType.java<br />
src/main/java/org/projectzion/game/persitence/entities/NodeType.java<br />
src/main/java/org/projectzion/game/persitence/entities/NodeTypeReward.java<br />
src/main/java/org/projectzion/game/persitence/entities/rewards/Reward.java<br /></code>

<h2>Roadmap</h2>
Finish the current open todos (authentication, user-management, sessions)<br />
Flyway-Repo for initial configuration<br />
Sample Game Client<br />
