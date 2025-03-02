// Clear the database
MATCH (n)
DETACH DELETE n;

// Create Measurement Unit nodes
CREATE (:MeasurementUnit {name: 'g'});
CREATE (:MeasurementUnit {name: 'ml'});
CREATE (:MeasurementUnit {name: 'kcal'});
CREATE (:MeasurementUnit {name: 'mg'});

// Create Nutrient nodes
CREATE (:Nutrient {name: 'Protein'});
CREATE (:Nutrient {name: 'Fat'});
CREATE (:Nutrient {name: 'Carbohydrate'});
CREATE (:Nutrient {name: 'Calories'});
CREATE (:Nutrient {name: 'Magnesium'});

// Create Ingredient nodes
CREATE (:Ingredient {name: 'Chicken Breast'});
CREATE (:Ingredient {name: 'Milk'});
CREATE (:Ingredient {name: 'Olive Oil'});
CREATE (:Ingredient {name: 'Rice'});
CREATE (:Ingredient {name: 'Spinach'});

// Create Dish nodes
CREATE (:Dish {name: 'Chicken Breast with Spinach', cookedOn: '2025-02-25'});
CREATE (:Dish {name: 'Milk', cookedOn: '2025-02-25'});

// Create Meal nodes
CREATE (:Meal {name: 'Lunch'});

// Create utility node to account for weight change while cooking
CREATE (:Rku {name: 'Weight change, 1g'});

// Create Nutrient record-keeping units
MATCH (mu_g :MeasurementUnit {name: 'g'}),
      (n_prot :Nutrient {name: 'Protein'})
CREATE (rku :Rku {name: 'Protein, 1g'})
CREATE (n_prot) -[:MEASURED_IN]-> (rku)
CREATE (mu_g) -[:MEASURES] -> (rku);

MATCH (mu_g :MeasurementUnit {name: 'g'}),
      (n_fat :Nutrient {name: 'Fat'})
CREATE (rku :Rku {name: 'Fat, 1g'})
CREATE (n_fat) -[:MEASURED_IN]-> (rku)
CREATE (mu_g) -[:MEASURES] -> (rku);

MATCH (mu_g :MeasurementUnit {name: 'g'}),
      (n_carbs :Nutrient {name: 'Carbohydrate'})
CREATE (rku :Rku {name: 'Carbs, 1g'})
CREATE (n_carbs) -[:MEASURED_IN]-> (rku)
CREATE (mu_g) -[:MEASURES] -> (rku);

MATCH (mu_kcal :MeasurementUnit {name: 'kcal'}),
      (n_calories :Nutrient {name: 'Calories'})
CREATE (rku :Rku {name: 'Calories, 1kcal'})
CREATE (n_calories) -[:MEASURED_IN]-> (rku)
CREATE (mu_kcal) -[:MEASURES] -> (rku);

MATCH (mu_mg :MeasurementUnit {name: 'mg'}),
      (n_mg :Nutrient {name: 'Magnesium'})
CREATE (rku :Rku {name: 'Magnesium, 1mg'})
CREATE (n_mg) -[:MEASURED_IN]-> (rku)
CREATE (mu_mg) -[:MEASURES] -> (rku);

// Create Ingredient record-keeping units
MATCH (mu_g :MeasurementUnit {name: 'g'}),
      (i_chick :Ingredient {name: 'Chicken Breast'}),
      (i_rice :Ingredient {name: 'Rice'}),
      (i_spin :Ingredient {name: 'Spinach'})
CREATE (rku_chick :Rku {name: 'Chicken Breast, 1g'})
CREATE (i_chick) -[:MEASURED_IN]-> (rku_chick)
CREATE (mu_g) -[:MEASURES] -> (rku_chick)
CREATE (rku_rice :Rku {name: 'Rice, 1g'})
CREATE (i_rice) -[:MEASURED_IN]-> (rku_rice)
CREATE (mu_g) -[:MEASURES] -> (rku_rice)
CREATE (rku_spin :Rku {name: 'Spinach, 1g'})
CREATE (i_spin) -[:MEASURED_IN]-> (rku_spin)
CREATE (mu_g) -[:MEASURES] -> (rku_spin);

MATCH (mu_ml :MeasurementUnit {name: 'ml'}),
      (i_milk :Ingredient {name: 'Milk'}),
      (i_oil :Ingredient {name: 'Olive Oil'})
CREATE (rku_milk :Rku {name: 'Milk, 1ml'})
CREATE (i_milk) -[:MEASURED_IN]-> (rku_milk)
CREATE (mu_ml) -[:MEASURES] -> (rku_milk)
CREATE (rku_oil :Rku {name: 'Olive Oil, 1ml'})
CREATE (i_oil) -[:MEASURED_IN]-> (rku_oil)
CREATE (mu_ml) -[:MEASURES] -> (rku_oil);

// Create Composition (Nutrients in Ingredients relations)
MATCH (rku_chick :Rku {name: 'Chicken Breast, 1g'}),
      (rku_milk :Rku {name: 'Milk, 1ml'}),
      (rku_prot  :Rku {name: 'Protein, 1g'})
CREATE (c :Composition {name: 'Proteins in Chicken Breast'})
CREATE (rku_chick) -[:CONTAINER_OF {amount: 100}]-> (c)
CREATE (rku_prot) -[:COMPONENT_OF {amount: 30}]-> (c)
CREATE (c1 :Composition {name: 'Proteins in Milk'})
CREATE (rku_milk) -[:CONTAINER_OF {amount: 200}]-> (c1)
CREATE (rku_prot) -[:COMPONENT_OF {amount: 6}]-> (c1);

MATCH (rku_chick :Rku {name: 'Chicken Breast, 1g'}),
      (rku_fat  :Rku {name: 'Fat, 1g'})
CREATE (c :Composition {name: 'Fat in Chicken Breast'})
CREATE (rku_chick) -[:CONTAINER_OF {amount: 100}]-> (c)
CREATE (rku_fat) -[:COMPONENT_OF {amount: 3}]-> (c);

MATCH (rku_oil :Rku {name: 'Olive Oil, 1ml'}),
      (rku_calories  :Rku {name: 'Calories, 1kcal'})
CREATE (c :Composition {name: 'Calories in Olive Oil'})
CREATE (rku_oil) -[:CONTAINER_OF {amount: 15}]-> (c)
CREATE (rku_calories) -[:COMPONENT_OF {amount: 120}]-> (c);

MATCH (rku_rice :Rku {name: 'Rice, 1g'}),
      (rku_carbs  :Rku {name: 'Carbs, 1g'})
CREATE (c :Composition {name: 'Carbs in Rice'})
CREATE (rku_rice) -[:CONTAINER_OF {amount: 15}]-> (c)
CREATE (rku_carbs) -[:COMPONENT_OF {amount: 120}]-> (c);

MATCH (rku_spin :Rku {name: 'Spinach, 1g'}),
      (rku_magn  :Rku {name: 'Magnesium, 1mg'})
CREATE (c :Composition {name: 'Magnesium in Spinach'})
CREATE (rku_spin) -[:CONTAINER_OF {amount: 15}]-> (c)
CREATE (rku_magn) -[:COMPONENT_OF {amount: 120}]-> (c);

MATCH (d1 :Dish {name: 'Chicken Breast with Spinach'}),
      (rku_chick :Rku {name: 'Chicken Breast, 1g'}),
      (rku_spin :Rku {name: 'Spinach, 1g'}),
      (rku_util :Rku {name: 'Weight change, 1g'})
CREATE (c1 :Composition {name: 'D01 Composition'})
CREATE (d1) -[:CONTAINER_OF {amount: 730.0}]-> (c1)
CREATE (rku_chick) -[:COMPONENT_OF {amount: 350.0}] -> (c1)
CREATE (rku_spin) -[:COMPONENT_OF {amount: 500.0}] -> (c1)
CREATE (rku_util) -[:COMPONENT_OF {amount: -120.0}] -> (c1);

MATCH (d:Dish {name: 'Milk'}),
      (rku_milk:Rku {name: 'Milk, 1ml'})
CREATE (c:Composition {name: 'D02 Composition'})
CREATE (d) -[:CONTAINER_OF {amount: 200.0}]-> (c)
CREATE (rku_milk) -[:COMPONENT_OF {amount: 200.0}]-> (c);

MATCH (m:Meal {name: 'Lunch'}),
      (d_chick:Dish {name: 'Chicken Breast with Spinach'}),
      (d_milk:Dish {name: 'Milk'})
CREATE (c:Composition {name: '2025-02-25 Lunch'})
CREATE (m) -[:CONTAINER_OF]-> (c)
CREATE (d_chick) -[:COMPONENT_OF {amount: 200.0}]-> (c)
CREATE (d_milk) -[:COMPONENT_OF {amount: 250.0}]-> (c);