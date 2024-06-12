package de.tinf22b6.dhbwhub.proposal.simplified_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportPostProposal {
	private String reportReason;

	private String reportDescription;

	private Long postId;

	private Long authorId;

	private Long userId;

	private String type;
}
