package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import projet.data.Benevole;
import projet.data.Club;
import projet.data.Epreuve;
import projet.data.EquipeBenevole;
import projet.data.Participant;
import projet.data.Permi;
import projet.data.Service;
import projet.data.Tache;


@Mapper
public interface IMapper {  
	
	
	Service update( @MappingTarget Service target, Service source );
	
	Benevole update( @MappingTarget Benevole target, Benevole source );
	
	Participant update( @MappingTarget Participant target, Participant source );
	
	EquipeBenevole update( @MappingTarget EquipeBenevole target, EquipeBenevole source );
	
	Permi update( @MappingTarget Permi target, Permi source );
	
	Epreuve update( @MappingTarget Epreuve target, Epreuve source );
	
	Tache update( @MappingTarget Tache target, Tache source );

	Club update( @MappingTarget Club courant, Club club);
}
