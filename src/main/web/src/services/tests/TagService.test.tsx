import React from "react";
import '@testing-library/jest-dom';
import { handleTagInput } from "../TagService";

describe("handleTagInput", (): void => {
  let setTags: jest.Mock;
  let setNewTag: jest.Mock;
  let setTagError: jest.Mock;

  beforeEach((): void => {
    setTags = jest.fn();
    setNewTag = jest.fn();
    setTagError = jest.fn();
  });

  const triggerEnterEvent = (value: string, tags: string[] = []): void => {
    const event: React.KeyboardEvent<HTMLInputElement> = {
      key: "Enter",
      preventDefault: jest.fn()
    } as unknown as React.KeyboardEvent<HTMLInputElement>;

    handleTagInput(event, value, setTags, setNewTag, setTagError, tags);
  };

  test("should add a valid tag", (): void => {
    triggerEnterEvent("Validtag", []);

    expect(setTags).toHaveBeenCalledWith(["Validtag"]);
    expect(setNewTag).toHaveBeenCalledWith("");
    expect(setTagError).toHaveBeenCalledWith("");
  });

  test("should not add an empty tag", (): void => {
    triggerEnterEvent("", []);

    expect(setTags).not.toHaveBeenCalled();
    expect(setNewTag).not.toHaveBeenCalled();
    expect(setTagError).toHaveBeenCalledWith("🚫 Tag must not be empty.");
  });

  test("should not add a one letter tag", (): void => {
    triggerEnterEvent("A", []);

    expect(setTags).not.toHaveBeenCalled();
    expect(setNewTag).not.toHaveBeenCalled();
    expect(setTagError).toHaveBeenCalledWith("🚫 Tag must be a word.");
  });

  test("should not add a tag with less than 3 letters", (): void => {
    triggerEnterEvent("Ab", []);

    expect(setTags).not.toHaveBeenCalled();
    expect(setNewTag).not.toHaveBeenCalled();
    expect(setTagError).toHaveBeenCalledWith("🚫 Tag must have at least 3 letters.");
  });

  test("should not add a tag with spaces", (): void => {
    triggerEnterEvent("Two Words", []);

    expect(setTags).not.toHaveBeenCalled();
    expect(setNewTag).not.toHaveBeenCalled();
    expect(setTagError).toHaveBeenCalledWith("🚫 Tag must be a single word.");
  });

  test("should not add a tag without a capital letter", (): void => {
    triggerEnterEvent("lowercase", []);

    expect(setTags).not.toHaveBeenCalled();
    expect(setNewTag).not.toHaveBeenCalled();
    expect(setTagError).toHaveBeenCalledWith("🚫 Tag must begin with a capital letter.");
  });

  test("should not add a tag with non-letter characters", (): void => {
    triggerEnterEvent("Invalid1", []);

    expect(setTags).not.toHaveBeenCalled();
    expect(setNewTag).not.toHaveBeenCalled();
    expect(setTagError).toHaveBeenCalledWith("🚫 Tag must only contain letters.");
  });

  test("should not add a tag with umlauts", (): void => {
    triggerEnterEvent("Über", []);

    expect(setTags).not.toHaveBeenCalled();
    expect(setNewTag).not.toHaveBeenCalled();
    expect(setTagError).toHaveBeenCalledWith("🚫 Tag must not contain umlauts.");
  });

  test("should not add a tag with more than 12 letters", (): void => {
    triggerEnterEvent("Verylongtagname", []);

    expect(setTags).not.toHaveBeenCalled();
    expect(setNewTag).not.toHaveBeenCalled();
    expect(setTagError).toHaveBeenCalledWith("🚫 Tag can have a maximum of 12 letters.");
  });

  test("should not add a tag that already exists", (): void => {
    triggerEnterEvent("Existingtag", ["Existingtag"]);

    expect(setTags).not.toHaveBeenCalled();
    expect(setNewTag).not.toHaveBeenCalled();
    expect(setTagError).toHaveBeenCalledWith("🚫 Tag already exists.");
  });
});