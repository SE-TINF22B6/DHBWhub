import { useLexicalComposerContext } from "@lexical/react/LexicalComposerContext";
import React, { useCallback, useEffect, useRef, useState } from "react";
import {CAN_REDO_COMMAND, CAN_UNDO_COMMAND, REDO_COMMAND, UNDO_COMMAND,
    $getSelection, $isRangeSelection} from "lexical";
import { $getNearestNodeOfType, mergeRegister } from "@lexical/utils";
import { $isListNode, ListNode } from "@lexical/list";
import { $isHeadingNode } from "@lexical/rich-text";

const LowPriority = 1;

export default function ToolbarPlugin() {
    const [editor] = useLexicalComposerContext();
    const toolbarRef = useRef(null);
    const [canUndo, setCanUndo] = useState(false);
    const [canRedo, setCanRedo] = useState(false);
    const [blockType, setBlockType] = useState("paragraph"); // eslint-disable-line no-unused-vars
    const [selectedElementKey, setSelectedElementKey] = useState(null); // eslint-disable-line no-unused-vars
    const [showBlockOptionsDropDown, setShowBlockOptionsDropDown] = useState(false); // eslint-disable-line no-unused-vars

    const updateToolbar = useCallback(() => {
        const selection = $getSelection();
        if ($isRangeSelection(selection)) {
            const anchorNode = selection.anchor.getNode();
            const element =
                anchorNode.getKey() === "root"
                    ? anchorNode
                    : anchorNode.getTopLevelElementOrThrow();
            const elementKey = element.getKey();
            const elementDOM = editor.getElementByKey(elementKey);
            if (elementDOM !== null) {
                setSelectedElementKey(elementKey);
                if ($isListNode(element)) {
                    const parentList = $getNearestNodeOfType(anchorNode, ListNode);
                    const type = parentList ? parentList.getTag() : element.getTag();
                    setBlockType(type);
                } else {
                    const type = $isHeadingNode(element)
                        ? element.getTag()
                        : element.getType();
                    setBlockType(type);
                }
            }
        }
    }, [editor]);

    useEffect(() => {
        return mergeRegister(
            editor.registerUpdateListener(({ editorState }) => {
                editorState.read(() => {
                    updateToolbar();
                });
            }),
            editor.registerCommand(
                CAN_UNDO_COMMAND,
                (payload) => {
                    setCanUndo(payload);
                    return false;
                },
                LowPriority
            ),
            editor.registerCommand(
                CAN_REDO_COMMAND,
                (payload) => {
                    setCanRedo(payload);
                    return false;
                },
                LowPriority
            )
        );
    }, [editor, updateToolbar]);

    return (
        <div className="toolbar" ref={toolbarRef}>
            <button
                disabled={!canUndo}
                onClick={() => {
                    editor.dispatchCommand(UNDO_COMMAND, null);
                }}
                className="toolbar-item spaced"
                aria-label="Undo"
            >
                <i className="format undo" />
            </button>
            <button
                disabled={!canRedo}
                onClick={() => {
                    editor.dispatchCommand(REDO_COMMAND, null);
                }}
                className="toolbar-item"
                aria-label="Redo"
            >
                <i className="format redo" />
            </button>
        </div>
    );
}