import { Component, forwardRef, Input, KeyValueDiffer, KeyValueDiffers, OnInit } from '@angular/core';
import { ControlValueAccessor, FormControl, FormsModule, NG_VALIDATORS, NG_VALUE_ACCESSOR, ReactiveFormsModule } from '@angular/forms';

type InputTypes = "text" | "email" | "password"

@Component({
  selector: 'app-login-input',
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
      {
        provide: NG_VALUE_ACCESSOR,
        useExisting: forwardRef(() => LoginInputComponent),
        multi: true,
      }
    ],
  templateUrl: './login-input.component.html',
  styleUrl: './login-input.component.css'
})
export class LoginInputComponent implements ControlValueAccessor {
  @Input() type: InputTypes = "text";
  @Input() inputId: string = "";
  @Input() placeholder: string = "";
  @Input() label: string = "";
  @Input() required: boolean = false;
  @Input() invalidMessage: string | null = null;

  value: string = "";
  onChange: any = () => {};
  onTouched: any = () => {};

  onInput(event: Event){
    const value = (event.target as HTMLInputElement).value;
    this.onChange(value);
  }

  writeValue(value: any): void {
    this.value = value;
  }

  registerOnChange(fn: any): void {
    this.onChange = fn
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn
  }

  setDisabledState(isDisabled: boolean): void {}
}
