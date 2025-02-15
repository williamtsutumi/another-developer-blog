import { Component, inject } from '@angular/core';
import { Title } from "@angular/platform-browser";
import { LoginService } from "../../../services/login.service";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginInputComponent } from '../../login-input/login-input.component';
import { LoginLayoutComponent } from '../../login-layout/login-layout.component';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

interface LoginForm {
  email: FormControl,
  password: FormControl
}

@Component({
  selector: 'app-login',
  imports: [
    LoginLayoutComponent,
    LoginInputComponent,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  private loginService = inject(LoginService);
  private toastService = inject(ToastrService);
  loginForm!: FormGroup<LoginForm>;

  constructor(
    private titleService: Title,
    private router: Router
  ) {
    this.titleService.setTitle("Login");
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    });
  }
  
  onSubmit() {
    this.loginService.login(this.loginForm.value.email, this.loginForm.value.password).subscribe({
      next: () => {
        this.toastService.success("Login feito com sucesso!");
        this.router.navigate(['/home']);
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
