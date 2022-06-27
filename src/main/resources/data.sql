-- insert Ingredient table
INSERT INTO RECIPE (ID, TITLE, IS_VEGETARIAN, NO_OF_SERVING, INSTRUCTIONS ) VALUES
(1001,'Tahona - Morita aioli', TRUE, 2, 'Place 1 cup of canned Morita chilis in a mixing bowl. Pour boiling water over chilis, to cover. Let steep for about 30 minutes or until the chilis rehydrate.'),
(1002,'Ranch 45 - Lemon Vinaigrette', TRUE, 3, 'Combine all ingredients in a mixing bowl, start with a few pinches of salt and pepper at first. Taste and adjust seasoning as necessary.'),
(1003,'Little Fish - Sesame Cucumber Salad', FALSE, 4, 'To prepare the cucumbers, cut them in half and deseed. Cut into bite size pieces and toss with 1/2 Tsp of Salt. Let the cucumbers sit out at room temperature for 30 minutes. This process draws out some of the water and concentrates the flavor of the cucumbers.'),
(1004,'Petite Peso - Mamon & Macerated Strawberries', FALSE, 2, 'Combine strawberries, two tablespoons of sugar, and a pinch of salt in a bowl and let sit at room temperature while you prepare the cake batter.'),
(1005,'Bubbly Garlic Bread', TRUE, 4, 'Place garlic, egg yolk, vinegar, and mustard in a blender or food processor.  Blend until smooth.  Continue to blend and slowly drizzle olive oil in until thick.'),
(1006,'Rainwater Manhattan', TRUE, 1, 'Twist an orange peel to release essential oils and rub around the rim of the glass. Add orange peel to glass as garnish.'),
(1007,'Watermelon Mint Salad', TRUE, 2, 'In a large bowl add watermelon cubes and zest half a large lemon, then squeeze the juice from the lemon Add 2 tbs. oil, mint, and arugula (if using) and toss until watermelon is evenly dressed'),
(1008,'Whole Wheat Jalapeño Cornbread', TRUE, 3, 'Preheat your oven to 375 degrees Fahrenheit. Combine 1st four dry ingredients in a large mixing bowl. Combine wet ingredients (agave through lemon juice) in a seperate mixing bowl, then mix into dry ingredients thoroughly.'),
(1009,'Moroccan Chicken Stew', FALSE, 3, 'Place frozen dish in fridge to thaw overnight. Pour contents into a saucepan and add 1/4 cup of low-sodium chicken broth or water. Place over medium heat and cover. (Note: if you like less spicy, you can remove some of the harissa paste using a spoon and reserve for later).'),
(1010,'Chicken Guisado', FALSE, 4, 'Place dish in fridge to thaw overnight or for 24 hours. Pour contents of container into a pot on the stove. Place over medium-high heat, bring to a simmer, reduce to medium and cook for approximately 10 minutes, stirring every 2-3 minutes. Cook until hot and the stew reaches an internal temperature of 165°F.');

-- insert Ingredient table
INSERT INTO INGREDIENT (ID, NAME) VALUES
(101,'Cooking Oil'),
(102,'Cheese'),
(103,'Butter'),
(104,'Rice Flour'),
(105,'Chiken'),
(106,'Corn Flour'),
(107,'Basil'),
(108,'Bay Leaves'),
(109,'Lamon Grass'),
(110,'Mint');

-- insert Ingredient table
INSERT INTO RECIPE_INGREDIENTS (RECIPE_ID, INGREDIENTS_ID) VALUES
(1001,101),
(1001,102),
(1001,106),
(1001,109),
(1002,103),
(1002,104),
(1002,105),
(1003,110),
(1003,109),
(1003,103),
(1003,101),
(1003,107),
(1004,104),
(1005,105),
(1006,106),
(1007,107),
(1008,108),
(1009,109),
(1001,101),
(1010,110);


