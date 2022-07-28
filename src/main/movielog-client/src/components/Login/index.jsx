import React, { useState, useRef } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";

import "./index.css";

function Login({ setNickname, setToken }) {
  const [loginError, setLoginError] = useState("");
  const emailInputRef = useRef();
  const pwInputRef = useRef();
  const navigate = useNavigate();

  const onClickLogin = (event) => {
    event.preventDefault();
    const enteredEmail = emailInputRef.current.value;
    const enteredPassword = pwInputRef.current.value;

    axios({
      method: "POST",
      url: "/api/login",
      data: {
        email: enteredEmail,
        password: enteredPassword,
      },
      headers: {
        "Content-type": "application/json",
      },
    })
      .then((response) => {
        setNickname(response.data.nickname);
        setToken(response.data.token);
        navigate("/");
      })
      .catch((error) => {
        if (error.response.data === 400) {
          setLoginError("아이디 또는 비밀번호를 다시 확인해주세요.");
        }
      });
  };

  return (
    <>
      <section className="login_page">
        <form onSubmit={onClickLogin}>
          <div className="login_area">
            <div className="loginId">
              <input
                type="text"
                name="userId"
                className="loginBtn_id"
                ref={emailInputRef}
                placeholder="아이디"
                required
              />
            </div>
            <div className="loginPw">
              <input
                type="password"
                name="password"
                ref={pwInputRef}
                className="loginBtn_pw"
                placeholder="비밀번호"
                required
              />
            </div>
          </div>
          <div className="login_error">{loginError}</div>
          <div className="loginBtn">
            <button onClick={onClickLogin}>로그인</button>
          </div>
        </form>
        <div className="loginBtn">
          <Link to="/join">회원가입</Link>
        </div>
      </section>
    </>
  );
}

export default Login;
