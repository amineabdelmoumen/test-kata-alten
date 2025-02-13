import { Component, OnInit, inject, signal } from "@angular/core";
import { Product } from "app/products/data-access/product.model";
import { ProductsService } from "app/products/data-access/products.service";
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component";
import { CartService } from "app/services/cart.service";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import { PaginatorModule } from 'primeng/paginator';
import { CommonModule } from '@angular/common';
const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [DataViewModule, CardModule, ButtonModule, DialogModule, ProductFormComponent, PaginatorModule,CommonModule],
})
export class ProductListComponent implements OnInit {
  private readonly productsService = inject(ProductsService);
  private readonly cartService = inject(CartService); 
  public readonly products = this.productsService.products;
  public paginatedProducts = signal<Product[]>([]);
  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);

  // Pagination variables
  public rowsPerPage = 5;
  public totalRecords = 0;
  public first = 0;

  // Selected products (shopping list)
  public selectedProducts = signal<Product[]>([]);

  ngOnInit() {
    this.productsService.get().subscribe((products) => {
      this.totalRecords = products.length;
      this.updatePaginatedProducts();
    });
  }

  // Update the paginated products based on the current page
  private updatePaginatedProducts() {
    const startIndex = this.first;
    const endIndex = startIndex + this.rowsPerPage;
    this.paginatedProducts.set(this.products().slice(startIndex, endIndex));
  }

  // Handle page change event
  public onPageChange(event: any) {
    this.first = event.first;
    this.rowsPerPage = event.rows;
    this.updatePaginatedProducts();
  }

  // Add a product to the selected list
  public onSelect(product: Product) {
    if (!this.selectedProducts().includes(product)) {
      this.selectedProducts.update((list) => [...list, product]);
    }
  }

  // Remove a product from the selected list
  public onRemoveFromSelection(product: Product) {
    this.selectedProducts.update((list) => list.filter((p) => p.id !== product.id));
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    this.productsService.delete(product.id).subscribe(() => {
      this.totalRecords--;
      this.updatePaginatedProducts();
    });
  }

  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe(() => {
        this.totalRecords++;
        this.updatePaginatedProducts();
      });
    } else {
      this.productsService.update(product).subscribe(() => {
        this.updatePaginatedProducts();
      });
    }
    this.closeDialog();
  }
  

  public onCancel() {
    this.closeDialog();
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }
  // Add a product to the cart
  public onSelectProduct(product: Product): void {
    this.selectedProducts.update((list) => [...list, product]);
    this.cartService.addProductToCart(product);
  }

  // Remove a product from the cart
  public onRemoveProductFromSelection(product: Product): void {
    this.cartService.removeProductFromCart(product);
  }
  // Add this method to your component class
getInventorySeverity(status: string) {
  switch (status.toLowerCase()) {
    case 'instock':
      return 'success';
    case 'lowstock':
      return 'warning';
    case 'outofstock':
      return 'danger';
    default:
      return 'info';
  }
}
}