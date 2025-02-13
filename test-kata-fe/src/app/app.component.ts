import {
  Component,
  inject,
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { CartService } from "./services/cart.service";
import { Product } from "./products/data-access/product.model";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { InputNumberModule } from "primeng/inputnumber";
import { InputTextareaModule } from "primeng/inputtextarea";
import { DropdownModule } from "primeng/dropdown";
import { FormsModule } from "@angular/forms";
import { DialogModule } from "primeng/dialog";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
   
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, FormsModule,
    ButtonModule,
    InputTextModule,
    DialogModule,
    InputNumberModule,
    InputTextareaModule,
    DropdownModule]
})
export class AppComponent {
  title = "ALTEN SHOP";
  private readonly cartService = inject(CartService); // Inject CartService

  // Get the cart item count
  public get cartItemCount(): number {
    return this.cartService.getCartItemCount();
    
  }
    // Sidebar visibility
    public isCartSidebarVisible = false;

    // Get the selected products from the CartService
    public selectedProducts = this.cartService.selectedProducts;
  
  
  
    // Toggle the cart sidebar
    public toggleCartSidebar(): void {
      this.isCartSidebarVisible = !this.isCartSidebarVisible;
    }
  
    // Remove a product from the cart
    public removeProductFromCart(product: Product): void {
      this.cartService.removeProductFromCart(product);
    }
}
