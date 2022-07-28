import { postLogin } from "./userInfo";
import { getMovie } from "./movieInfo";

const useMock = true;
const api = {
  postLogin,
  // getMovie,
};

const mockApi = {
  postLogin: (_id, _pw, callback) => {
    return new Promise(
      (resolve) =>
        setTimeout(() => {
          callback({ nickname: "userNickname", userId: "2" });
          resolve();
        }),
      1000
    );
  },

  getMovies: (callback) => {
    setTimeout(
      () =>
        callback({
          movie: [
            {
              no: 1,
              title: "해리 포터와 혼혈 왕자",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0679/67900_P01_130458.jpg",
              subtitle: "<b>Harry</b> Potter And The Half-Blood Prince",
              pub_date: 2009,
              director: "데이빗 예이츠|",
              actor: "다니엘 래드클리프|엠마 왓슨|루퍼트 그린트|",
              user_rating: 6.97,
              price: 1000,
            },
            {
              no: 2,
              title: "해리 포터와 불사조 기사단",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0570/E7095-00.jpg",
              subtitle: "<b>Harry</b> Potter And The Order Of The Phoenix",
              pub_date: 2007,
              director: "데이빗 예이츠|",
              actor: "다니엘 래드클리프|엠마 왓슨|루퍼트 그린트|",
              user_rating: 7.04,
              price: 2000,
            },
            {
              no: 3,
              title: "해리 포터와 불의 잔",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0378/37883_P158_182652.jpg",
              subtitle: "<b>Harry</b> Potter And The Goblet Of Fire",
              pub_date: 2005,
              director: "마이크 뉴웰|",
              actor: "다니엘 래드클리프|엠마 왓슨|루퍼트 그린트|",
              user_rating: 8.04,
              price: 3000,
            },
            {
              no: 4,
              title: "해리 포터와 아즈카반의 죄수",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0355/35546_P88_142722.jpg",
              subtitle: "<b>Harry</b> Potter And The Prisoner Of Azkaban",
              pub_date: 2004,
              director: "알폰소 쿠아론|",
              actor: "다니엘 래드클리프|엠마 왓슨|루퍼트 그린트|",
              user_rating: 8.75,
              price: 4000,
            },
            {
              no: 5,
              title: "해리 포터와 비밀의 방",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0339/C3930-06.jpg",
              subtitle: "<b>Harry</b> Potter And The Chamber Of Secrets",
              pub_date: 2002,
              director: "크리스 콜럼버스|",
              actor: "다니엘 래드클리프|루퍼트 그린트|엠마 왓슨|",
              user_rating: 8.9,
              price: 5000,
            },
            {
              no: 6,
              title: "해리 포터와 마법사의 돌",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0306/30688_P28_142632.jpg",
              subtitle: "<b>Harry</b> Potter And The Sorcerer's Stone",
              pub_date: 2001,
              director: "크리스 콜럼버스|",
              actor: "다니엘 래드클리프|루퍼트 그린트|엠마 왓슨|",
              user_rating: 9.36,
              price: 6000,
            },
            {
              no: 7,
              title: "<b>신비한 동물</b>들과 덤블도어의 비밀",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1641/164122_P03_160045.jpg",
              subtitle: "Fantastic Beasts: The Secrets of Dumbledore",
              pub_date: 2022,
              director: "데이빗 예이츠|",
              actor:
                "주드 로|에디 레드메인|에즈라 밀러|매즈 미켈슨|댄 포글러|앨리슨 수돌|칼럼 터너|제시카 윌리엄스|빅토리아 예이츠|윌리엄 나딜람|리처드 코일|",
              user_rating: 6.5,
              price: 7000,
            },
            {
              no: 8,
              title: "<b>신비한 동물</b>들과 그린델왈드의 범죄",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1542/154255_P24_095824.jpg",
              subtitle: "Fantastic Beasts: The Crimes of Grindelwald",
              pub_date: 2018,
              director: "데이빗 예이츠|",
              actor:
                "에디 레드메인|조니 뎁|캐서린 워터스턴|주드 로|에즈라 밀러|",
              user_rating: 6.17,
              price: 8000,
            },
            {
              no: 9,
              title: "예티: <b>신비한 동물</b> 탐험대",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1491/149121_P12_115050.jpg",
              subtitle: "Mission Kathmandu",
              pub_date: 2017,
              director: "피에르 그레코|낸시 플로렌스 사바르|",
              actor: "이다은|박성영|",
              user_rating: 9,
              price: 9000,
            },
            {
              no: 10,
              title: "<b>신비한 동물</b>사전",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1156/115642_P40_141424.jpg",
              subtitle: "Fantastic Beasts and Where to Find Them",
              pub_date: 2016,
              director: "데이빗 예이츠|",
              actor: "에디 레드메인|캐서린 워터스턴|콜린 파렐|앨리슨 수돌|",
              user_rating: 8.18,
              price: 10000,
            },
            {
              no: 11,
              title: "셜록 홈즈 3",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1082/108230_P01_144456.jpg",
              subtitle: "<b>Sherlock</b> Holmes 3",
              pub_date: 2020,
              director: "덱스터 플레처|",
              actor: "로버트 다우니 주니어|주드 로|",
              user_rating: 9.49,
              price: 11000,
            },
            {
              no: 12,
              title: "극장판 천재 추리 탐정 셜록홈즈",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1954/195491_P14_153906.jpg",
              subtitle: "<b>Sherlock</b> Holmes &amp; the Great Escape",
              pub_date: 2019,
              director: "원건도|추영조|",
              actor: "석승훈|김용|서정익|박시윤|",
              user_rating: 9.39,
              price: 12000,
            },
            {
              no: 13,
              title: "셜록 놈즈",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1586/158620_P19_101107.jpg",
              subtitle: "<b>Sherlock</b> Gnomes",
              pub_date: 2018,
              director: "존 스티븐슨|",
              actor: "조니 뎁|에밀리 블런트|제임스 맥어보이|",
              user_rating: 8.48,
              price: 13000,
            },
            {
              no: 14,
              title: "셜록: 유령신부",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1432/143245_P21_135147.jpg",
              subtitle: "<b>SHERLOCK</b>",
              pub_date: 2015,
              director: "더글러스 맥키넌|",
              actor: "베네딕트 컴버배치|마틴 프리먼|",
              user_rating: 7.15,
              price: 14000,
            },
            {
              no: 15,
              title: "셜록 시즌3",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1082/108230_P01_144456.jpg",
              subtitle: "<b>Sherlock</b>",
              pub_date: 2014,
              director: "폴 맥기건|",
              actor:
                "베네딕트 컴버배치|마틴 프리먼|루퍼트 그레이브즈|앤드류 스캇|우나 스텁스|루 브릴리|라스 미켈슨|",
              user_rating: 9.92,
              price: 15000,
            },
            {
              no: 16,
              title: "셜록 시즌2",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0921/92189_P01_104907.jpg",
              subtitle: "<b>Sherlock</b>",
              pub_date: 2012,
              director: "폴 맥기건|",
              actor: "베네딕트 컴버배치|마틴 프리먼|",
              user_rating: 9.9,
              price: 16000,
            },
            {
              no: 17,
              title: "셜록홈즈 : 그림자 게임",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0735/73508_P30_154625.jpg",
              subtitle: "<b>Sherlock</b> Holmes: A Game of Shadows",
              pub_date: 2011,
              director: "가이 리치|",
              actor: "로버트 다우니 주니어|주드 로|",
              user_rating: 8,
              price: 17000,
            },
            {
              no: 18,
              title: "셜록 시즌1",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0812/81202_P01_112130.jpg",
              subtitle: "<b>Sherlock</b>",
              pub_date: 2010,
              director: "폴 맥기건|",
              actor: "베네딕트 컴버배치|마틴 프리먼|",
              user_rating: 9.82,
              price: 18000,
            },
            {
              no: 19,
              title: "톰과 제리 설록홈즈를 만나다",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0915/91586_P01_181356.jpg",
              subtitle: "Tom and Jerry Meet <b>Sherlock</b> Holmes",
              pub_date: 2010,
              director: "스파이크 브랜트|제프 서지|",
              actor: "마이클 요크|말콤 맥도웰|",
              user_rating: 7.22,
              price: 19000,
            },
            {
              no: 20,
              title: "셜록홈즈-비밀의 열쇠",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0762/76263_P01_105346.jpg",
              subtitle: "<b>Sherlock</b> Holmes",
              pub_date: 2010,
              director: "레이첼 골덴버그|",
              actor: "벤 사이더|가레스 데이비드 로이드|",
              user_rating: 3.25,
              price: 20000,
            },
            {
              no: 21,
              title: "셜록 홈즈",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0511/51132_P31_200710.jpg",
              subtitle: "<b>Sherlock</b> Holmes",
              pub_date: 2009,
              director: "가이 리치|",
              actor:
                "로버트 다우니 주니어|주드 로|레이첼 맥아담스|마크 스트롱|",
              user_rating: 8.19,
              price: 21000,
            },
            {
              no: 22,
              title: "셜록 홈즈 앤 더 베이커 스트리트 이레귤러스",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1060/106057_P01_181939.jpg",
              subtitle:
                "<b>Sherlock</b> Holmes and the Baker Street Irregulars",
              pub_date: 2007,
              director: "줄리안 켐프|",
              actor: "조나단 프라이스|빌 패터슨|",
              user_rating: 5.5,
              price: 22000,
            },
            {
              no: 23,
              title: "셜록: 죽음의 덫",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0775/77555_P06_163711.jpg",
              subtitle: "<b>Sherlock</b>",
              pub_date: 2002,
              director: "그레이엄 데이크스튼|",
              actor: "제임스 다시|로저 모리지|",
              user_rating: 4.08,
              price: 23000,
            },
            {
              no: 24,
              title: "<b>닥터 스트레인지</b>: 대혼돈의 멀티버스",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1820/182016_P12_172809.jpg",
              subtitle: "Doctor Strange in the Multiverse of Madness",
              pub_date: 2022,
              director: "샘 레이미|",
              actor: "베네딕트 컴버배치|엘리자베스 올슨|",
              user_rating: 7.7,
              price: 24000,
            },
            {
              no: 25,
              title: "<b>닥터 스트레인지</b>",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1254/125459_P19_102404.jpg",
              subtitle: "Doctor Strange",
              pub_date: 2016,
              director: "스콧 데릭슨|",
              actor:
                "베네딕트 컴버배치|틸다 스윈튼|치웨텔 에지오포|레이첼 맥아담스|매즈 미켈슨|",
              user_rating: 8.72,
              price: 25000,
            },
            {
              no: 26,
              title: "<b>토르</b>: 러브 앤 썬더",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1873/187347_P01_103714.jpg",
              subtitle: "Thor: Love and Thunder",
              pub_date: 2022,
              director: "타이카 와이티티|",
              actor:
                "크리스 헴스워스|테사 톰슨|나탈리 포트만|크리스찬 베일|크리스 프랫|",
              user_rating: 9.83,
              price: 26000,
            },
            {
              no: 27,
              title: "<b>토르</b>: 라그나로크",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1348/134898_P22_111152.png",
              subtitle: "Thor: Ragnarok",
              pub_date: 2017,
              director: "타이카 와이티티|",
              actor: "크리스 헴스워스|톰 히들스턴|케이트 블란쳇|마크 러팔로|",
              user_rating: 8.85,
              price: 27000,
            },
            {
              no: 28,
              title: "<b>토르</b>: 다크 월드",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0958/95873_P10_135653.jpg",
              subtitle: "Thor: The Dark World",
              pub_date: 2013,
              director: "앨런 테일러|",
              actor:
                "크리스 헴스워스|나탈리 포트만|톰 히들스턴|안소니 홉킨스|크리스토퍼 에클리스턴|이드리스 엘바|",
              user_rating: 8.35,
              price: 28000,
            },
            {
              no: 29,
              title: "<b>토르</b>: 천둥의 신",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0699/69989_P13_161342.jpg",
              subtitle: "Thor",
              pub_date: 2011,
              director: "케네스 브래너|",
              actor:
                "크리스 헴스워스|나탈리 포트만|톰 히들스턴|안소니 홉킨스|스텔란 스카스가드|캣 데닝스|",
              user_rating: 7.33,
              price: 29000,
            },
            {
              no: 30,
              title: "아이언맨 3",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0702/70254_P35_153229.jpg",
              subtitle: "<b>Iron Man</b> 3",
              pub_date: 2013,
              director: "셰인 블랙|",
              actor:
                "로버트 다우니 주니어|기네스 팰트로|벤 킹슬리|돈 치들|가이 피어스|레베카 홀|",
              user_rating: 8.86,
              price: 30000,
            },
            {
              no: 31,
              title: "아이언맨 2",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0490/49008_P52_162130.jpg",
              subtitle: "<b>Iron Man</b> 2",
              pub_date: 2010,
              director: "존 파브로|",
              actor:
                "로버트 다우니 주니어|기네스 팰트로|돈 치들|스칼릿 조핸슨|미키 루크|",
              user_rating: 7.37,
              price: 31000,
            },
            {
              no: 32,
              title: "아이언맨",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0448/D4885-01.jpg",
              subtitle: "<b>Iron Man</b>",
              pub_date: 2008,
              director: "존 파브로|",
              actor:
                "로버트 다우니 주니어|테렌스 하워드|제프 브리지스|기네스 팰트로|",
              user_rating: 8.93,
              price: 32000,
            },
            {
              no: 33,
              title: "<b>반지의 제왕</b>: 왕의 귀환",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0317/31796_P74_100250.jpg",
              subtitle: "The Lord Of The Rings: The Return Of The King",
              pub_date: 2003,
              director: "피터 잭슨|",
              actor:
                "일라이저 우드|숀 애스틴|앤디 서키스|이안 맥켈런|리브 타일러|비고 모텐슨|올랜도 블룸|존 라이스 데이비스|빌리 보이드|도미닉 모나한|버나드 힐|미란다 오토|",
              user_rating: 9.38,
              price: 33000,
            },
            {
              no: 34,
              title: "<b>반지의 제왕</b>: 두 개의 탑",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0317/31795_P38_100048.jpg",
              subtitle: "The Lord Of The Rings: The Two Towers",
              pub_date: 2002,
              director: "피터 잭슨|",
              actor: "일라이저 우드|이안 맥켈런|비고 모텐슨|",
              user_rating: 9.35,
              price: 34000,
            },
            {
              no: 35,
              title: "비욘드 더 무비 - <b>반지의 제왕</b>",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0639/63951_P01_090942.jpg",
              subtitle:
                "National Geographic: Beyond The Movie - The Lord Of The Rings",
              pub_date: 2002,
              director: "리자 코스|",
              actor: "필 크로리|존 호우|",
              user_rating: 7.92,
              price: 35000,
            },
            {
              no: 36,
              title: "<b>반지의 제왕</b>: 반지 원정대",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0317/31794_P49_134515.jpg",
              subtitle: "The Lord Of The Rings: The Fellowship Of The Ring",
              pub_date: 2001,
              director: "피터 잭슨|",
              actor:
                "일라이저 우드|이안 맥켈런|리브 타일러|비고 모텐슨|숀 애스틴|케이트 블란쳇|",
              user_rating: 9.3,
              price: 36000,
            },
            {
              no: 37,
              title: "토이 스토리 4",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1019/101966_P09_114632.jpg",
              subtitle: "<b>Toy Story</b> 4",
              pub_date: 2019,
              director: "조시 쿨리|",
              actor: "톰 행크스|애니 파츠|토니 헤일|팀 알렌|",
              user_rating: 9.02,
              price: 37000,
            },
            {
              no: 38,
              title: "토이 스토리 공룡 전사들의 도시",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1307/130784_P00_112236.jpg",
              subtitle: "<b>Toy Story</b> That Time Forgot",
              pub_date: 2014,
              director: "스티브 퍼셀|",
              actor: "톰 행크스|크리스틴 스칼|팀 알렌|티모시 달튼|조안 쿠삭|",
              user_rating: 8.43,
              price: 38000,
            },
            {
              no: 39,
              title: "토이 스토리 공포의 대탈출",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1019/101967_P00_112121.jpg",
              subtitle: "<b>Toy Story</b> of Terror",
              pub_date: 2013,
              director: "앤거스 맥클레인|",
              actor: "톰 행크스|팀 알렌|조안 쿠삭|",
              user_rating: 9.35,
              price: 39000,
            },
            {
              no: 40,
              title: "파티사우루스 렉스",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/1501/150159_P01_175457.jpg",
              subtitle: "Partysaurus Rex",
              pub_date: 2012,
              director: "마크 A. 월쉬|",
              actor:
                "톰 행크스|팀 알렌|월리스 쇼운|코리 버튼|토니 콕스|도널드 풀리러브|에밀리 한|돈 리클스|",
              user_rating: 9.2,
              price: 40000,
            },
            {
              no: 41,
              title: "토이 스토리 3",
              image:
                "https://ssl.pstatic.net/imgmovie/mdi/mit110/0664/66463_P46_190114.jpg",
              subtitle: "<b>Toy Story</b> 3",
              pub_date: 2010,
              director: "리 언크리치|",
              actor: "톰 행크스|팀 알렌|조안 쿠삭|",
              user_rating: 9.4,
              price: 41000,
            },
          ],
        }),
      1000
    );
  },
};

const defaultApi = useMock ? mockApi : api;

export default defaultApi;
