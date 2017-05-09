package eudat.b2sharev2.model.linguistics;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import eudat.b2sharev2.model.AlternateIdentifier;
import eudat.b2sharev2.model.CommunitySpecific;
import eudat.b2sharev2.model.Contributor;
import eudat.b2sharev2.model.Creator;
import eudat.b2sharev2.model.Description;
import eudat.b2sharev2.model.Record;
import eudat.b2sharev2.model.ResourceType;
import eudat.b2sharev2.model.Title;

public class LinguisticsRecord extends Record {
	public static final String LINGUISTICS_COMMUNITY_ID="0afede87-2bf2-4d89-867e-d2ee57251c62";
	public static final String LINGUISTICS_COMMUNITY_SCHEMA_ID="2a01ee91-36fe-4edb-9734-73d22ac78821";
	
//	public class LinguisticComunityFields {
//		String languageCode;
//		String linguisticResourceType;;
//
//		public void setLanguageCode(String code) {
//			// TODO Auto-generated method stub
//			
//		}
//
//	}
	
	LinguisticsCommunityFields communityFields;
	
	public LinguisticsRecord() {
		setTitles(new HashSet<>());
		setResourceTypes(new HashSet<>());
		setCommunity(LinguisticsRecord.LINGUISTICS_COMMUNITY_ID);
		communityFields=new LinguisticsCommunityFields();
		setCommunitySpecific(new CommunitySpecific());
		getCommunitySpecific().setAdditionalProperty(LINGUISTICS_COMMUNITY_SCHEMA_ID , communityFields);	
		communityFields.setLingResourceType(new HashSet<>());
	}
	
	@JsonIgnore
	public LinguisticsCommunityFields getCommunityFields() {
		return communityFields;
	}
	
	@Override
	public Set<Contributor> getContributors() {
		Set<Contributor> set = super.getContributors();
		if (set==null) {
			set=new HashSet<>();
			setContributors(set);
		}
		return set;
	}
	
	@Override
	public Set<Creator> getCreators() {
		Set<Creator> set = super.getCreators();
		if (set==null) {
			set=new HashSet<>();
			setCreators(set);
		}
		return set;
	}

	@Override
	public Set<AlternateIdentifier> getAlternateIdentifiers() {
		Set<AlternateIdentifier> set = super.getAlternateIdentifiers();
		if (set==null) {
			set=new HashSet<>();
			setAlternateIdentifiers(set);
		}
		return set;
	}
	
	@Override
	public Set<Description> getDescriptions() {
		Set<Description> set = super.getDescriptions();
		if (set==null) {
			set=new HashSet<>();
			setDescriptions(set);
		}
		return set;
	}
	
	@Override
	public Set<String> getDisciplines() {
		Set<String> set = super.getDisciplines();
		if (set==null) {
			set=new HashSet<>();
			setDisciplines(set);
		}
		return set;
	}
	
	@Override
	public Set<String> getKeywords() {
		Set<String> set = super.getDisciplines();
		if (set==null) {
			set=new HashSet<>();
			setDisciplines(set);
		}
		return set;
	}
	
	@Override
	public Set<ResourceType> getResourceTypes() {
		Set<ResourceType> set = super.getResourceTypes();
		if (set==null) {
			set=new HashSet<>();
			setResourceTypes(set);
		}
		return set;
	}
	
	@Override
	public Set<Title> getTitles() {
		Set<Title> set = super.getTitles();
		if (set==null) {
			set=new HashSet<>();
			setTitles(set);
		}
		return set;
	}
	
	
	
	
}
