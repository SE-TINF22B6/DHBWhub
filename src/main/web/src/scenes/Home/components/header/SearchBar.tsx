import React, {useState} from "react";
import "./SearchBar.css";

export const SearchBar = (): JSX.Element => {
  const [query, setQuery] = useState("");

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>): void => {
    if (event.key === "Enter") {
      submitSearch();
    }
  };

  const submitSearch = (): void => {
    if (query.trim() !== "") {
      // eslint-disable-next-line no-restricted-globals
      const currentSearch = new URLSearchParams(location.search);
      currentSearch.set("query", query);
      const queryString = currentSearch.toString();
      // eslint-disable-next-line no-restricted-globals
      window.location.href = queryString ? `${window.location.origin}/search?${queryString}` : window.location.origin;
      setQuery("");
    }
  };

  return (
      <div className="searchbar">
        <input
            className="input-field"
            placeholder="Type here to search..."
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            onKeyDown={handleKeyDown}
        />
        <div className="search-icon" onClick={submitSearch} />
      </div>
  );
};