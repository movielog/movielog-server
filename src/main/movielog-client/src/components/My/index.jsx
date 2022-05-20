import React from "react";
import { Link, Outlet } from "react-router-dom";
import "./index.css";

function My() {
  return (
    <>
      <section className="my">
        <div className="my_area">
          <ul className="my_menu">
            <li>
              <Link to="/my/order">구매내역</Link>
            </li>
            <li>
              <Link to="/my/review">내 리뷰</Link>
            </li>
          </ul>
        </div>
        <Outlet />
      </section>
    </>
  );
}

export default My;
