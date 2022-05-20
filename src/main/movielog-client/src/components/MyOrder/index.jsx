import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "./index.css";

function MyOrder({ token }) {
  const [myOrder, setMyOrder] = useState([]);

  useEffect(() => {
    axios({
      method: "GET",
      url: "/api/my/order",
      headers: {
        "Content-type": "application/json",
        "X-AUTH-TOKEN": token,
      },
    }).then((response) => {
      setMyOrder(response.data);
    });
  }, [token]);

  return (
    <>
      <section className="my_order_box">
        <div className="order_area">
          {myOrder.map((list) => (
            <Link key={list.orderId} to={`/my/order/${list.orderId}`}>
              <li>
                <p className="my_order_title">{list.title}</p>
                <div className="my_order_money">
                  <p>금액</p>
                  {list.price}
                </div>
                <div className="my_order_date">
                  <p>구매일자</p>
                  {list.date}
                </div>
                <div className="my_order_status">
                  <p>상태</p>
                  {list.status}
                </div>
              </li>
            </Link>
          ))}
        </div>
      </section>
    </>
  );
}

export default MyOrder;
