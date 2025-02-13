import { CommonModule } from "@angular/common";
import { Component, signal } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { RouterLink } from "@angular/router";
import { Contact } from "app/products/data-access/product.model";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DataViewModule } from "primeng/dataview";
import { DialogModule } from "primeng/dialog";
import { PaginatorModule } from "primeng/paginator";

const emptyContact: Contact = {
  id: 0,
  email: "",
  message: "",
};

@Component({
  selector: "contact",
  templateUrl: "./contact.component.html",
  styleUrls: ["./contact.component.scss"],
  standalone: true,
  imports: [
    DataViewModule,
    CardModule,
    ButtonModule,
    FormsModule,
    DialogModule,
    PaginatorModule,
    CommonModule,
  ],
})
export class ContactComponent {
  // Initialize an empty contacts signal
  public readonly contacts = signal<Contact[]>([]);
   // Flag to track if the user has submitted the form
   public isSubmitted = false;
  public readonly appTitle = "Contact";

  // Placeholder for the new contact
  public newContact = signal<Contact>(emptyContact);

  // Method to save a new contact
  public onSave(): void {
    this.isSubmitted = true;
    if (this.newContact().email && this.newContact().message) {
      // Get the current contacts from the signal
      const updatedContacts = [...this.contacts(), {
        ...this.newContact(),
        id: this.contacts().length + 1  // Generate a unique ID
      }];
      
      // Update the signal with the new contacts array
      this.contacts.set(updatedContacts);

      // Reset the newContact after saving
      this.newContact.set(emptyContact);

      // Show success alert
      alert("Demande de contact envoyée avec succès!");
    } else {
      alert("Merci de remplir les 2 champs!");
    }
  }
}
