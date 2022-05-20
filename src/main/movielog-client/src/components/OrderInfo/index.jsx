import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import "./index.css";

function OrderInfo({ token }) {
  const [movie, setMovie] = useState([]);
  const params = useParams();
  const orderId = Number(params.no);

  useEffect(() => {
    axios({
      method: "GET",
      url: `/api/my/order/${orderId}`,
      headers: {
        "Content-type": "application/json",
        "X-AUTH-TOKEN": token,
      },
    }).then((response) => {
      setMovie(response.data);
    });
  }, [orderId, token]);

  return (
    <>
      <section className="order_info">
        <p>구매상세정보</p>
        <div className="order_info_area">
          <div className="order_info_date">
            <div className="order_info_title">결제일자</div>
            <span>{movie.date}</span>
          </div>
          <div className="order_info_box">
            <img src={movie.image} alt="" />
            <div className="order_info">
              <span>영화 제목</span>
              <div className="order_info_detail">{movie.title}</div>
            </div>
            <div className="order_info">
              <span>가격</span>
              <div className="order_info_detail">{movie.price}</div>
            </div>
            <div className="order_info">
              <span>구매상태</span>
              <div className="order_info_detail">{movie.status}</div>
            </div>
          </div>
          <div className="order_purchase_info">
            <div className="order_info_title">결제금액정보</div>
            <div className="order_purchase_info_detail">
              <span>주문금액</span>
              <span>{movie.price}</span>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default OrderInfo;
