import { useState } from "react";

function useInput(valueCheck) {
  const [inputValue, setInputValue] = useState("");
  const [isClicked, setIsClicked] = useState(false);

  const valueIsValid = valueCheck(inputValue);
  const hasError = !valueIsValid && isClicked;

  const handleValueChange = (event) => {
    setInputValue(event.target.value);
  };

  const handleInputBlur = (event) => {
    setIsClicked(true);
  };

  const reset = () => {
    setInputValue("");
    setIsClicked(false);
  };

  return {
    value: inputValue,
    isValid: valueIsValid,
    hasError,
    handleValueChange,
    handleInputBlur,
    reset,
  };
}

export default useInput;
