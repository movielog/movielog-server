import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import { useParams, useNavigate, Link } from "react-router-dom";
import "./index.css";

function ReviewWrite({ token }) {
  const [alertMsg, setAlertMsg] = useState("");
  const [write, setWrite] = useState({
    title: "",
    content: "",
  });
  const [getMovieTitle, setGetMovieTitle] = useState("");
  const [alertTitle, setAlertTitle] = useState(false);
  const [alertContent, setAlertContent] = useState(false);

  const titleInputRef = useRef();
  const contentInputRef = useRef();

  const params = useParams();
  const movieId = Number(params.no);
  const navigate = useNavigate();

  useEffect(() => {
    axios({
      method: "GET",
      url: `/api/review/write/${movieId}`,
      headers: {
        "Content-type": "application/json",
        "X-AUTH-TOKEN": token,
      },
    }).then((response) => {
      setGetMovieTitle(response.data.title);
    });
  }, [movieId, token]);

  const handleOnChange = (e) => {
    setWrite({
      ...write,
      [e.target.name]: e.target.value,
    });
  };

  const handleWrite = (event) => {
    const enteredTitle = titleInputRef.current.value;
    const enteredContent = contentInputRef.current.value;

    if (write.title.length < 1 && write.content.length < 1) {
      setAlertTitle(true);
      setAlertContent(true);
      setAlertMsg("1글자 이상 입력해주세요");
      event.preventDefault();
    } else if (write.title.length < 1) {
      setAlertTitle(true);
      setAlertMsg("1글자 이상 입력해주세요");
      event.preventDefault();
    } else if (write.content.length < 1) {
      setAlertContent(true);
      setAlertMsg("1글자 이상 입력해주세요");
      event.preventDefault();
    } else {
      axios({
        method: "POST",
        url: `/api/review/write/${movieId}`,
        data: {
          title: enteredTitle,
          content: enteredContent,
        },
        headers: {
          "Content-type": "application/json",
          "X-AUTH-TOKEN": token,
        },
      });
      event.preventDefault();
      navigate("/my/review");
    }
  };

  return (
    <>
      <section className="write">
        <form className="write_form">
          <input
            type="text"
            value={getMovieTitle}
            className="no_alert"
            readOnly
          />
          <input
            type="text"
            name="title"
            value={write.title}
            ref={titleInputRef}
            onChange={handleOnChange}
            className={alertTitle ? "alert" : "no_alert"}
            placeholder="제목"
          />
          <textarea
            name="content"
            value={write.content}
            ref={contentInputRef}
            onChange={handleOnChange}
            className={alertContent ? "alert" : "no_alert"}
            placeholder="내용"
          />
          <div className="write_btn">
            <Link to="/review">
              <button>목록</button>
            </Link>
            <button onClick={handleWrite}>등록</button>
          </div>
          <div className="write_alert">{alertMsg}</div>
        </form>
      </section>
    </>
  );
}

export default ReviewWrite;
