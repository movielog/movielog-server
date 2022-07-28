import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./index.css";

const Profile = ({ token }) => {
  const [changeUser, setChangeUser] = useState([]);
  const nicknameInputRef = useRef();
  const navigate = useNavigate();

  const onChangeNickname = (event) => {
    setChangeUser({
      [event.target.name]: event.target.value,
    });
  };

  useEffect(() => {
    axios({
      method: "GET",
      url: "/api/user/me",
      headers: {
        "Content-type": "application/json",
        "X-AUTH-TOKEN": token,
      },
    }).then((response) => {
      setChangeUser(response.data);
    });
  }, [token]);

  const handleDelete = () => {
    axios({
      method: "DELETE",
      url: "/api/user",
      headers: {
        "Content-type": "application/json",
        "X-AUTH-TOKEN": token,
      },
    });
    navigate("/");
    window.location.reload();
  };

  const handleChange = (event) => {
    const enteredNickname = nicknameInputRef.current.value;
    axios({
      method: "POST",
      url: "/api/user/me",
      data: {
        nickname: enteredNickname,
      },
      headers: {
        "Content-type": "application/json",
        "X-AUTH-TOKEN": token,
      },
    });
    event.preventDefault();
    navigate("/user/me");
  };

  return (
    <>
      <section className="profile">
        <form>
          <div className="profile_box">
            <label htmlFor="inputUserId">아이디</label>
            <input
              type="text"
              value={changeUser.email}
              id="inputUserId"
              readOnly
            />
          </div>
          <div className="profile_box">
            <label htmlFor="inputNickname">닉네임</label>
            <input
              type="text"
              id="inputNickname"
              value={changeUser.nickname}
              onChange={onChangeNickname}
              ref={nicknameInputRef}
            />
          </div>
          <div className="joinBtn_area">
            <button type="submit" onClick={handleDelete}>
              탈퇴
            </button>
            <button type="submit" onClick={handleChange}>
              수정
            </button>
          </div>
        </form>
      </section>
    </>
  );
};

export default Profile;
