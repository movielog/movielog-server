import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import "./index.css";
import axios from "axios";

function MovieDetail({ nickname }) {
  const [movieData, setMovieData] = useState([]);
  const params = useParams();
  const movieId = Number(params.no);

  useEffect(() => {
    axios({
      method: "GET",
      url: `/api/movie/${movieId}`,
      data: {
        movieId: movieId,
      },
      headers: {
        "Content-type": "application/json",
      },
    }).then((response) => {
      setMovieData(response.data);
    });
  }, [movieId]);

  return (
    <>
      <section className="movie_detail">
        <div className="detail_area">
          <div className="detail_box">
            <img src={movieData.image} alt="" />
            <div className="detail_info">
              <div className="info_title">{movieData.title}</div>
              <div className="info_subtitle">{movieData.subtitle}</div>
              <div className="info_director">{movieData.director}</div>
              <div className="info_actor">{movieData.actor}</div>
              <div className="info_data">{movieData.pub_date}</div>
              <div className="info_rating">
                <span className="material-icons-outlined">star</span>
                {movieData.user_rating}
              </div>
            </div>
          </div>
        </div>
        {nickname ? (
          <div className="button_box">
            <Link to={`/review/write/${movieData.movieId}`}>
              <button className="detail_review">
                리뷰<span className="material-icons-outlined">edit</span>
              </button>
            </Link>
            <Link to={`/order/${movieData.movieId}`}>
              <button className="detail_order">
                구매
                <span className="material-icons-outlined">shopping_bag</span>
              </button>
            </Link>
          </div>
        ) : (
          <></>
        )}
      </section>
    </>
  );
}

export default MovieDetail;
