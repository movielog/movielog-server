import React, { useState, useEffect } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Header from "./components/Header";
import Main from "./components/Main";
import MovieDetail from "./components/MovieDetail";
import MovieOrder from "./components/MovieOrder";
import Login from "./components/Login";
import Join from "./components/Join";
import My from "./components/My";
import MyOrder from "./components/MyOrder";
import OrderInfo from "./components/OrderInfo";
import MyReview from "./components/MyReview";
import ReviewBoard from "./components/ReviewBoard";
import ReviewWrite from "./components/ReviewWrite";
import Profile from "./components/Profile";

function App() {
  const [nickname, setNickname] = useState("");
  const [token, setToken] = useState("");

  useEffect(() => {
    setNickname(localStorage.getItem("nickname"));
    setToken(localStorage.getItem("token"));
  }, []);

  return (
    <>
      <BrowserRouter>
        <Header nickname={nickname} />
        <Routes>
          <Route path="/" element={<Main />} />
          <Route
            path="/movie/:no"
            element={<MovieDetail nickname={nickname} />}
          />
          <Route
            path="/review/write/:no"
            element={<ReviewWrite token={token} />}
          />
          <Route path="/order/:no" element={<MovieOrder token={token} />} />
          <Route
            path="/user/me"
            element={<Profile token={token} nickname={nickname} />}
          />
          <Route path="/join" element={<Join />} />
          <Route
            path="/login"
            element={<Login setNickname={setNickname} setToken={setToken} />}
          />
          <Route path="/my" element={<My nickname={nickname} token={token} />}>
            <Route path="order" element={<MyOrder token={token} />} />
            <Route path="order/:no" element={<OrderInfo token={token} />} />
            <Route path="review" element={<MyReview token={token} />} />
          </Route>
          <Route path="/review" element={<ReviewBoard />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
