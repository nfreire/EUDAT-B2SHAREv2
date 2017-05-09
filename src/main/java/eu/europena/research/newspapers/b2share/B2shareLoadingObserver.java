package eu.europena.research.newspapers.b2share;

import java.util.Date;
import java.util.List;

import eu.europena.research.newspapers.DataProvider;
import eu.europena.research.newspapers.Issue;
import eu.europena.research.newspapers.Title;
import eudat.b2sharev2.DraftRecord;

public class B2shareLoadingObserver {
	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(B2shareLoadingObserver.class);
	
	StatisticCalcMean issuesLoadingStats=new StatisticCalcMean();
	long issueStartTime;
	StatisticCalcMean titlesLoadingStats=new StatisticCalcMean();
	long titleStartTime;
	long titleDraftDuration;
	
	public void start(DataProvider dp) {
		log.info(dp.getLabel());
	}

	public void start(Issue i) {
		log.info(i.getLabel());
		issueStartTime=new Date().getTime();
	}

	public void end(Issue i, DraftRecord draftOfIssue) {
		long issueLoadingDuration= new Date().getTime() - issueStartTime;		
		issuesLoadingStats.enter(issueLoadingDuration);
		log.info("Issue time: "+issueLoadingDuration+" ms");
	}

	public long getIssuesLoadingAverage() {
		return (long) issuesLoadingStats.getMean();
	}

	public void start(Title t) {
		log.info(t.getLabel());		
	}


	public void end(DataProvider dp) {
		log.info("All data provider titles loaded. Average time per issue: "+ issuesLoadingStats.getMean());
		log.info("All data provider titles loaded. Average time per title: "+ titlesLoadingStats.getMean());
	}

	public void startDraft(Title t) {
		titleStartTime=new Date().getTime();
	}
	

	public void endDraft(Title t, DraftRecord titleDraft) {
		titleDraftDuration= new Date().getTime() - titleStartTime;		
		log.info("Title draft time: "+titleDraftDuration+" ms");
	}

	public void startPublish(DraftRecord titleDraft, Title t, List<String> issuesPids) {
		titleStartTime=new Date().getTime();
	}

	public void end(Title t, DraftRecord titleDraft) {
		long titlePublishDuration = new Date().getTime() - titleStartTime;		
		titlesLoadingStats.enter(titleDraftDuration + titlePublishDuration);
		log.info("Title (archive) time: "+(titleDraftDuration + titlePublishDuration)+" ms");
		log.info("All title issues loaded. Average time per issue: "+ issuesLoadingStats.getMean());
	}
	
}
