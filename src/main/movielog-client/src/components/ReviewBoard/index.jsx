import React, { useEffect, useState } from "react";
import axios from "axios";
import "./index.css";

function ReviewBoard() {
  const [reviewList, setReviewList] = useState([]);

  useEffect(() => {
    axios({
      method: "GET",
      url: "/api/review",
      headers: {
        "Content-type": "application/json",
      },
    }).then((response) => {
      setReviewList(response.data);
    });
  }, []);

  return (
    <>
      <section className="review_board">
        <ul>
          {reviewList.map((review) => (
            <li key={review.reviewId}>
              <div className="board_user">
                <p>닉네임</p>
                {review.nickname}
              </div>
              <div className="board_movie">
                <p>{review.movietitle}</p>
              </div>
              <div className="board_title">
                <p>리뷰 제목</p>
                {review.title}
              </div>
              <div className="board_content">
                <p>리뷰 내용</p>
                {review.content}
              </div>
            </li>
          ))}
        </ul>
      </section>
    </>
  );
}

export default ReviewBoard;
