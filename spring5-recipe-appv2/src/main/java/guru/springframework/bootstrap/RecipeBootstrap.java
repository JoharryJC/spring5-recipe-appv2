package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	
	public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}
		
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
		
	}

	private List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<>(2);
		
		//get UOMs
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
		
		if (!eachUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found"); 
		} 
		
		Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		
		if (!tableSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found"); 
		} 
		
		Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		if (!teaSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found"); 
		} 
		
		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
		
		if (!dashUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found"); 
		} 
		
		Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
		
		if (!pintUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found"); 
		} 
		
		Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
		
		if (!cupsUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found"); 
		} 
		
		//get optionals
		UnitOfMeasure eachUom = eachUomOptional.get(); 
		UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure teapoonUom = teaSpoonUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pintUom = pintUomOptional.get();
		UnitOfMeasure cupsUom = cupsUomOptional.get();
		
		//get Categories
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		
		if (!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not found");
			
		}
		
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		
		if (!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not found");
		}
		
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		
		//Yummy Guac
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole"); 
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(0);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setDirections("1. Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. "
				+ "Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. \r\n"
				+ "\r\n"
				+ "2. Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\r\n"
				+ "\r\n"
				+ "3. Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. "
				+ "The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n"
				+ "\r\n"
				+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. "
				+ "So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\r\n"
				+ "\r\n"
				+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. "
				+ "Start with this recipe and adjust to your taste.\r\n"
				+ "\r\n"
				+ "4. Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole "
				+ "and press down to cover it and to prevent air reaching it. "
				+ "(The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve."
				+ "\n" 
				+ "Read more: http://www.simplyrecipes.com/recipes/" 
				);
		
		Notes guacNotes = new Notes();
		guacNotes.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not "
				+ "touch your eyes or the area near your eyes with your hands for several hours."
				+ "\n"
				+ "Read more: http://www.simplyrecipes.com/recipes/");
		
		//guacNotes.setRecipe(guacRecipe);
		guacRecipe.setNotes(guacNotes);
				
		guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("kosher Salt", new BigDecimal(2), teapoonUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("Fresh Lime Juice or Lemon Juice", new BigDecimal(2), tableSpoonUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("minced red onion or ...", new BigDecimal(2), tableSpoonUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds", new BigDecimal(2), eachUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom, guacRecipe));
		guacRecipe.addIngredient(new Ingredient("ripe tomato seeds and pulp removed", new BigDecimal(2), eachUom, guacRecipe));
		
		
		guacRecipe.getCategories().add(americanCategory);
		guacRecipe.getCategories().add(mexicanCategory);
		
		//add to return list
		recipes.add(guacRecipe);
		
		//Yummy Tacos
		Recipe tacosRecipe = new Recipe();
		tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
		tacosRecipe.setCookTime(9);
		tacosRecipe.setPrepTime(20);
		tacosRecipe.setDifficulty(Difficulty.MODERATE);
		
		tacosRecipe.setDirections("1. Prepare a gas or charcoal grill for medium-high, direct heat.\r\n"
				+ "2. Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, "
				+ "oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. "
				+ "Add the chicken to the bowl and toss to coat all over.\r\n"
				+ "\r\n"
				+ "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\r\n"
				+ "\r\n"
				+ "3. Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer "
				+ "inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\r\n"
				+ "\r\n"
				+ "4. Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. "
				+ "As soon as you see pockets of the air start to puff up in the tortilla, "
				+ "turn it with tongs and heat for a few seconds on the other side.\r\n"
				+ "\r\n"
				+ "Wrap warmed tortillas in a tea towel to keep them warm until serving.\r\n"
				+ "\r\n"
				+ "5. Assemble the tacos:\r\n"
				+ "Slice the chicken into strips. On each tortilla, place a small handful of arugula. "
				+ "Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. "
				+ "Drizzle with the thinned sour cream. Serve with lime wedges."
				+ ""
				+ "\n" 
				+ " and mix it in with your mashed avocados."
				+ "Once you have basic guacamole down, feel free to experiment with variations "
				+ "\n"
				+ "including strawberries, peaches, pineapple, mangoes, even watermelon. "
				+ "\n"
				+ "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
		
		Notes tacoNotes = new Notes();
		tacoNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, "
				+ "on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, "
				+ "and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)"
				+ "\n"
				+ "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
		
		//tacoNotes.setRecipe(tacosRecipe);
		tacosRecipe.setNotes(tacoNotes);		
		
		tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom, tacosRecipe));
		tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(2), teapoonUom, tacosRecipe));
		tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(2), teapoonUom, tacosRecipe));
		tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(2), teapoonUom, tacosRecipe));
		tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(2), teapoonUom, tacosRecipe));
		tacosRecipe.addIngredient(new Ingredient("Clove of Garlic", new BigDecimal(2), eachUom, tacosRecipe));
		
		tacosRecipe.getCategories().add(americanCategory);
		tacosRecipe.getCategories().add(mexicanCategory); 
		
		recipes.add(tacosRecipe);
		
		return recipes;
		
		
	}
	
	
	
	
	
	
}
