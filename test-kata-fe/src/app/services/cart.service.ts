import { Injectable, signal } from '@angular/core';
import { Product } from 'app/products/data-access/product.model'; 

@Injectable({
  providedIn: 'root',
})
export class CartService {
  // Signal to hold the selected products
  public selectedProducts = signal<Product[]>([]);

  // Add a product to the cart
  public addProductToCart(product: Product): void {
    this.selectedProducts.update((products) => [...products, product]);
  }

  // Remove a product from the cart
  public removeProductFromCart(product: Product): void {
    this.selectedProducts.update((products) =>
      products.filter((p) => p.id !== product.id)
    );
  }

  // Get the number of selected products
  public getCartItemCount(): number {
    return this.selectedProducts().length;
  }
}