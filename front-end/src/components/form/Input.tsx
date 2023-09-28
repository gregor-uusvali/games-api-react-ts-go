import { ChangeEventHandler, FocusEventHandler, Ref, forwardRef } from "react";

interface InputProps {
  name: string,
  type: string,
  title: string,
  className: string,
  placeholder: string,
  onChange: ChangeEventHandler<HTMLInputElement>,
  onFocus?: FocusEventHandler<HTMLInputElement>, // Add onFocus prop
  onBlur?: FocusEventHandler<HTMLInputElement>, // Add onBlur prop
  autoComplete: string,
  value: string,
  errorDiv: string,
  errorMsg: string,

}

const Input = forwardRef((props: InputProps, ref: Ref<HTMLInputElement>) => {
  return (
    <div className="mb-3">
      <label htmlFor={props.name} className="block text-gray-700 text-sm font-bold mb-1">
        {props.title}
      </label>
      <input
        type={props.type}
        className={props.className}
        id={props.name}
        ref={ref}
        name={props.name}
        placeholder={props.placeholder}
        onChange={props.onChange}
        autoComplete={props.autoComplete}
        value={props.value}
        onFocus={props.onFocus} // Pass onFocus prop
        onBlur={props.onBlur}   // Pass onBlur prop
      />
      <div className={props.errorDiv}>{props.errorMsg}</div>
    </div>
  )
})

export default Input