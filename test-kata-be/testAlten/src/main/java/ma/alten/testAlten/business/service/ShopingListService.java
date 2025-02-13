package ma.alten.testAlten.business.service;

import ma.alten.testAlten.business.domain.ShoppingList;

import java.util.List;

public interface ShopingListService {
    public List<ShoppingList> getAllShoppingLists();

    public ShoppingList getShoppingListById(Long id);

    public ShoppingList saveShoppingList(ShoppingList shoppingList);

    public void deleteShoppingList(Long id);

    public ShoppingList addProductToShoppingList(Long shoppingListId, Long productId);

    public ShoppingList removeProductFromShoppingList(Long shoppingListId, Long productId);

}
