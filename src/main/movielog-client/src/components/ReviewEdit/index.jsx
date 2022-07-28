import React, { useState, useRef } from "react";
import "./index.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function ReviewEdit({
  reviewId,
  reviewTitle,
  reviewContent,
  movieTitle,
  token,
}) {
  const [userReview, setUserReview] = useState({
    title: reviewTitle,
    content: reviewContent,
    movie: movieTitle,
  });
  const [isBtnClick, setIsBtnClick] = useState(false);
  const titleInputRef = useRef();
  const contentInputRef = useRef();
  const navigate = useNavigate();

  const onChangeReview = (event) => {
    setUserReview({
      [event.target.name]: event.target.value,
    });
  };

  const handleDelete = () => {
    axios({
      method: "DELETE",
      url: `/api/my/review/${reviewId}`,
      headers: {
        "Content-type": "application/json",
        "X-AUTH-TOKEN": token,
      },
    });
    navigate("/review");
  };

  const handleEdit = () => {
    setIsBtnClick(!isBtnClick);
    const enteredTitle = titleInputRef.current.value;
    const enteredContent = contentInputRef.current.value;

    axios({
      method: "POST",
      url: `/api/my/review/${reviewId}`,
      data: {
        title: enteredTitle,
        content: enteredContent,
      },
      headers: {
        "Content-type": "application/json",
        "X-AUTH-TOKEN": token,
      },
    });
    navigate("/review");
  };

  return (
    <>
      <div className="review_edit_movie">
        <p>{userReview.movie}</p>
      </div>
      {isBtnClick ? (
        <>
          <div className="review_edit_title">
            <p>리뷰 제목</p>
            <input
              type="text"
              value={userReview.title}
              onChange={onChangeReview}
              ref={titleInputRef}
            />
          </div>
          <div className="review_edit_content">
            <p>리뷰 내용</p>
            <textarea
              type="text"
              value={userReview.content}
              onChange={onChangeReview}
              ref={contentInputRef}
            />
          </div>
        </>
      ) : (
        <>
          <div className="review_edit_title">
            <p>리뷰 제목</p>
            {userReview.title}
          </div>
          <div className="review_edit_content">
            <p>리뷰 내용</p>
            {userReview.content}
          </div>
        </>
      )}
      <div className="review_edit_btn">
        <button type="submit" onClick={handleDelete}>
          삭제
        </button>
        <button type="submit" onClick={handleEdit}>
          수정
        </button>
      </div>
    </>
  );
}

export default ReviewEdit;
