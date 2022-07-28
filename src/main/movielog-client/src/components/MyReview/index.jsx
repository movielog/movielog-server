import React, { useState, useEffect } from "react";
import axios from "axios";
import "./index.css";
import ReviewEdit from "../ReviewEdit";

function MyReview({ token }) {
  const [reviewList, setReviewList] = useState([]);

  useEffect(() => {
    axios({
      method: "GET",
      url: "/api/my/review",
      headers: {
        "Content-type": "application/json",
        "X-AUTH-TOKEN": token,
      },
    }).then((response) => {
      setReviewList(response.data);
    });
  }, [token]);

  return (
    <>
      <section className="my_review">
        <ul>
          {reviewList.map((list, index) => (
            <>
              <li key={index}>
                <ReviewEdit
                  reviewId={list.reviewId}
                  reviewTitle={list.title}
                  reviewContent={list.content}
                  movieTitle={list.movietitle}
                  token={token}
                />
              </li>
            </>
          ))}
        </ul>
      </section>
    </>
  );
}

export default MyReview;
