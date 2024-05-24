import React, {useEffect, useState} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {useLocation} from "react-router-dom";
import Lottie from "lottie-react";
import animationData from "../../assets/loading.json";
import {Footer} from "../../organisms/footer/Footer";
import {SearchSortOptions} from "./components/SearchSortOptions";
import {FindByOptions} from "./components/FindByOptions";
import {getJWT} from "../../services/AuthService";
import config from "../../config/config";

export const Search = () => {
  const location = useLocation();
  const searchParams: URLSearchParams = new URLSearchParams(location.search);
  const searchTerm: string | null = searchParams.get('query');
  const [searchResults, setSearchResults] = useState([]);
  const jwt: string | null = getJWT();
  const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  };

  const [notFound, setNotFound] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect((): void => {
    const fetchResults = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + `search/${searchTerm}`, {
          headers: headersWithJwt
        });
        if (response.ok) {
          setSearchResults(await response.json());
        } else {
          setNotFound(true);
        }
        setLoading(false);
      } catch (error) {
        console.error("Error when retrieving the search data:", error);
        setNotFound(true);
        setLoading(false);
      }
    };

    fetchResults();
  }, [searchTerm]);

  const [sortOption, setSortOption] = useState<string>('popular');
  const [findByOption, setFindByOption] = useState<string>('title');

  const handleSortChange = (option: string): void => {
    setSortOption(option);
  };

  const handleFindByChange = (option: string): void => {
    setFindByOption(option);
  };

  if (loading) {
    return (
        <div className="search">
          <Header/>
          <div className="loading-animation">
            <Lottie animationData={animationData}/>
          </div>
          <Footer/>
        </div>
    );
  }

  if (notFound || !searchTerm) {
    return (
        <div className="search">
          <Header/>
          <div className="search-content">
            <div className="search-sidebar">
              <SearchSortOptions onSortChange={handleSortChange}/>
              <FindByOptions onSortChange={handleFindByChange}/>
            </div>
            <div className="search-results">
              <h1 className="error">No results for search term: {searchTerm}</h1>
            </div>
          </div>
          <Footer/>
        </div>
    );
  }

  return (
      <div className="search">
        <Header/>
        <div className="search-sidebar">
          <SearchSortOptions onSortChange={handleSortChange}/>
          <FindByOptions onSortChange={handleFindByChange}/>
        </div>
        <Footer/>
      </div>
  );
};
