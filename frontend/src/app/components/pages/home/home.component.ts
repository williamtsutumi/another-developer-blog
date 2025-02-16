import { Component, inject } from '@angular/core';
import { HomeService } from '../../../tests/home.service';
import { ToastrService } from 'ngx-toastr';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  private homeService = inject(HomeService);
  private toastService = inject(ToastrService);

  constructor(
    private titleService: Title
  ) {
    this.titleService.setTitle("Home");
  }

  onSubmit() {
    this.homeService.sendRequest().subscribe({
      next: () => {
        this.toastService.success("Ok!");
      },
      error: (error) => {
        if (typeof error.error === 'string') {
          this.toastService.error(error.error);
        }
        else {
          this.toastService.error("Erro inesperado! Tente novamente mais tarde");
        }
      }
    });
  }
}
