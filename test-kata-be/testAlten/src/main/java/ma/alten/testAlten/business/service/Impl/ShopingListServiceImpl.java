package ma.alten.testAlten.business.service.Impl;

import ma.alten.testAlten.business.domain.Product;
import ma.alten.testAlten.business.domain.ShoppingList;
import ma.alten.testAlten.business.repository.ProductRepository;
import ma.alten.testAlten.business.repository.ShoppingListRepository;
import ma.alten.testAlten.business.service.ShopingListService;
import ma.alten.testAlten.security.domain.User;
import ma.alten.testAlten.security.repository.UserRepository;
import ma.alten.testAlten.security.utilities.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementation of the Shopping List service.
 * Handles CRUD operations for shopping lists and manages products within a shopping list.
 */
@Service
public class ShopingListServiceImpl implements ShopingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    /**
     * Constructs a new ShoppingListServiceImpl with required repositories.
     *
     * @param shoppingListRepository repository for shopping list operations
     * @param productRepository      repository for product operations
     * @param userRepository         repository for user operations
     */
    public ShopingListServiceImpl(ShoppingListRepository shoppingListRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all shopping lists.
     *
     * @return a list of all shopping lists
     */
    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListRepository.findAll();
    }

    /**
     * Retrieves a shopping list by its ID and checks user ownership.
     *
     * @param id the ID of the shopping list
     * @return the found shopping list
     * @throws NoSuchElementException if the shopping list is not found
     * @throws SecurityException      if the current user does not own the shopping list
     */
    public ShoppingList getShoppingListById(Long id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Shopping list not found"));
        checkUserOwnerShip(shoppingList);
        return shoppingList;
    }

    /**
     * Saves a shopping list.
     *
     * @param shoppingList the shopping list to save
     * @return the saved shopping list
     */
    public ShoppingList saveShoppingList(ShoppingList shoppingList) {
        return shoppingListRepository.save(shoppingList);
    }

    /**
     * Deletes a shopping list by its ID.
     *
     * @param id the ID of the shopping list
     * @throws NoSuchElementException if the shopping list is not found
     * @throws SecurityException      if the current user does not own the shopping list
     */
    public void deleteShoppingList(Long id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Shopping list not found"));
        checkUserOwnerShip(shoppingList);
        shoppingListRepository.deleteById(id);
    }

    /**
     * Adds a product to a shopping list.
     *
     * @param shoppingListId the ID of the shopping list
     * @param productId      the ID of the product to add
     * @return the updated shopping list
     * @throws RuntimeException   if the shopping list or product is not found
     * @throws SecurityException  if the current user does not own the shopping list
     */
    public ShoppingList addProductToShoppingList(Long shoppingListId, Long productId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new RuntimeException("Shopping List not found"));
        checkUserOwnerShip(shoppingList);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        shoppingList.getProducts().add(product);
        return shoppingListRepository.save(shoppingList);
    }

    /**
     * Removes a product from a shopping list.
     *
     * @param shoppingListId the ID of the shopping list
     * @param productId      the ID of the product to remove
     * @return the updated shopping list
     * @throws RuntimeException   if the shopping list or product is not found
     * @throws SecurityException  if the current user does not own the shopping list
     */
    public ShoppingList removeProductFromShoppingList(Long shoppingListId, Long productId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new RuntimeException("Shopping List not found"));
        checkUserOwnerShip(shoppingList);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        shoppingList.getProducts().remove(product);
        return shoppingListRepository.save(shoppingList);
    }

    /**
     * Checks if the current user owns the given shopping list.
     *
     * @param shoppingList the shopping list to check
     * @throws SecurityException if the user does not own the shopping list
     */
    private void checkUserOwnerShip(ShoppingList shoppingList) {
        User currentUser = userRepository.findByUsername(CurrentUser.getUserName());
        if (!currentUser.getId().equals(shoppingList.getId())) {
            throw new SecurityException("You don't own this shopping list");
        }
    }
}