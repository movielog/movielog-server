import React from "react";
import { Outlet, Link } from "react-router-dom";
import "./index.css";

function Header({ nickname }) {
  return (
    <>
      <header>
        <nav className="navbar">
          <Link className="navbar_logo" to="/">
            MovieLog
          </Link>
          <li className="navbar_menu">
            <Link to="/">영화</Link>
            <Link to="/review">리뷰</Link>
            {nickname ? <Link to="/my/order">MY</Link> : <></>}
          </li>
          {nickname ? (
            <>
              <li className="login">
                <Link to="/user/me">{nickname}님</Link>
              </li>
            </>
          ) : (
            <>
              <li className="login">
                <Link to="/join">회원가입</Link>
                <Link to="/login">로그인</Link>
              </li>
            </>
          )}
        </nav>
      </header>
      <Outlet />
    </>
  );
}

export default Header;
