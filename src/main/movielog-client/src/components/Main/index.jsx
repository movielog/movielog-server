import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "./index.css";

function Main() {
  const [movieData, setMovieData] = useState([]);

  useEffect(() => {
    axios({
      method: "GET",
      url: "/api/movie",
      headers: {
        "Content-type": "application/json",
      },
    }).then((response) => {
      setMovieData(response.data);
    });
  }, []);

  return (
    <>
      <main className="main">
        <ul>
          {movieData.map((movie) => (
            <Link key={movie.movieId} to={`/movie/${movie.movieId}`}>
              <li>
                <img src={movie.image} alt=""></img>
                <div className="main_info">
                  <div className="main_title">{movie.title}</div>
                  <div className="main_subtitle">{movie.subtitle}</div>
                  <div className="main_director">{movie.director}</div>
                </div>
              </li>
            </Link>
          ))}
        </ul>
      </main>
    </>
  );
}

export default Main;
