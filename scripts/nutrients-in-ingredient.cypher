// :param weight => 120.0;
// :param ingrName = 'Chicken Breast';

MATCH (:Ingredient {name: $ingrName})-[:MEASURED_IN]->(rku :Rku)
MATCH (rku)-[con :CONTAINER_OF]->(comp :Composition)
MATCH (comp)<-[cp :COMPONENT_OF]-(:Rku)<-[:MEASURED_IN]-(n: Nutrient)
RETURN n.name AS Nutrient,
       cp.amount * $weight / con.amount AS Amount