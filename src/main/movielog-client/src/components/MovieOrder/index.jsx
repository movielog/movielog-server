import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./index.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function MovieOrder({ token }) {
  const [movieData, setMovieData] = useState([]);
  const params = useParams();
  const movieId = Number(params.no);
  const navigate = useNavigate();

  useEffect(() => {
    axios({
      method: "GET",
      url: `/api/order/${movieId}`,
      headers: {
        "Content-type": "application/json",
        "X-AUTH-TOKEN": token,
      },
    }).then((response) => {
      setMovieData(response.data);
    });
  }, [movieId, token]);

  const handleOrder = () => {
    axios({
      method: "POST",
      url: `/api/order/${movieId}`,
      headers: {
        "Content-type": "application/json",
        "X-AUTH-TOKEN": token,
      },
    });
    navigate("/my/order");
  };

  return (
    <>
      <section className="movie_order_page">
        <p>주문정보</p>
        <div className="movie_order">
          <div className="order_box">
            <img src={movieData.image} alt="" />
            <div className="order_detail">
              <span>영화 제목</span>
              <div className="order_title">{movieData.title}</div>
              <div className="order_subtitle">{movieData.subtitle}</div>
            </div>
            <div className="order_detail">
              <span>가격</span>
              <div className="order_price">{movieData.price}원</div>
            </div>
          </div>
          <div className="order_button">
            <button onClick={handleOrder}>결제</button>
          </div>
        </div>
      </section>
    </>
  );
}

export default MovieOrder;
