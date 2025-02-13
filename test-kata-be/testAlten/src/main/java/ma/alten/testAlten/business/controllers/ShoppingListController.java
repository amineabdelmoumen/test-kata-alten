package ma.alten.testAlten.business.controllers;

import ma.alten.testAlten.business.domain.ShoppingList;
import ma.alten.testAlten.business.service.ShopingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppinglists")
public class ShoppingListController  {

    @Autowired
    private ShopingListService shoppingListService;

    // Get all shopping lists
    @GetMapping
    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListService.getAllShoppingLists();
    }

    // Get shopping list by ID
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingList> getShoppingListById(@PathVariable Long id) {
        ShoppingList shoppingList = shoppingListService.getShoppingListById(id);
        return ResponseEntity.ok(shoppingList);
    }

    // Create or update a shopping list
    @PostMapping
    public ResponseEntity<ShoppingList> saveShoppingList(@RequestBody ShoppingList shoppingList) {
        ShoppingList savedShoppingList = shoppingListService.saveShoppingList(shoppingList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShoppingList);
    }

    // Delete a shopping list
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingList(@PathVariable Long id) {
        shoppingListService.deleteShoppingList(id);
        return ResponseEntity.noContent().build();
    }

    // Add product to shopping list
    @PostMapping("/{shoppingListId}/products/{productId}")
    public ResponseEntity<ShoppingList> addProductToShoppingList(
            @PathVariable Long shoppingListId, @PathVariable Long productId) {
        ShoppingList updatedShoppingList = shoppingListService.addProductToShoppingList(shoppingListId, productId);
        return ResponseEntity.ok(updatedShoppingList);
    }

    // Remove product from shopping list
    @DeleteMapping("/{shoppingListId}/products/{productId}")
    public ResponseEntity<ShoppingList> removeProductFromShoppingList(
            @PathVariable Long shoppingListId, @PathVariable Long productId) {
        ShoppingList updatedShoppingList = shoppingListService.removeProductFromShoppingList(shoppingListId, productId);
        return ResponseEntity.ok(updatedShoppingList);
    }

}