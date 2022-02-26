package hu.oktatas.transport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import hu.oktatas.transport.model.Address;
import hu.oktatas.transport.model.Milestone;
import hu.oktatas.transport.model.Section;
import hu.oktatas.transport.model.TransportPlan;
import hu.oktatas.transport.repository.AddressRepository;
import hu.oktatas.transport.repository.MilestoneRepository;
import hu.oktatas.transport.repository.SectionRepository;
import hu.oktatas.transport.repository.TransportPlanRepository;

@Component
public class TestData {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	MilestoneRepository milestoneRepository;

	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	TransportPlanRepository transportPlanRepository;

	@EventListener
	public void appReady(ApplicationReadyEvent event) {

		List<Address> allAddress = new ArrayList<>();
		{
			allAddress.add(new Address((long) 1, "GM", "Goiás", "óvoda", "6000", "1", 5.0, 90.0));
			allAddress.add(new Address((long) 2, "GE", "Rio Grande do Sul", "kiskörősi", "6001", "2", 10.0, 85.0));
			allAddress.add(new Address((long) 3, "DE", "Mato Grosso", "szegedi", "6002", "3", 15.0, 80.0));
			allAddress.add(new Address((long) 4, "GH", "Rio Grande do Sul", "kőszegi", "6003", "4", 20.0, 75.0));
			allAddress.add(new Address((long) 5, "GI", "Paraná", "iskola", "6004", "5", 25.0, 70.0));
			allAddress.add(new Address((long) 6, "GR", "Mato Grosso do Sul", "alma", "6005", "6", 30.0, 65.0));
			allAddress.add(new Address((long) 7, "GL", "Bahia", "körte", "6006", "7", 35.0, 60.0));
			allAddress.add(new Address((long) 8, "GD", "Rio Grande do Sul", "narancs", "6007", "8", 40.0, 55.0));
			allAddress.add(new Address((long) 9, "GP", "Paraná", "barack", "6008", "9", 45.0, 50.0));
			allAddress.add(new Address((long) 10, "GU", "Santa Catarina", "óvoda", "6009", "10", 50.0, 45.0));
			allAddress.add(new Address((long) 11, "GT", "Sao Paulo", "kiskörősi", "6010", "11", 55.0, 40.0));
			allAddress.add(new Address((long) 12, "GG", "Rio Grande do Sul", "szegedi", "6011", "12", 60.0, 35.0));
			allAddress.add(new Address((long) 13, "GN", "Santa Catarina", "kőszegi", "6012", "13", 65.0, 30.0));
			allAddress.add(new Address((long) 14, "GW", "Rio de Janeiro", "iskola", "6013", "14", 70.0, 25.0));
			allAddress.add(new Address((long) 15, "GY", "Sao Paulo", "alma", "6014", "15", 75.0, 20.0));
			allAddress.add(new Address((long) 16, "HT", "Rio de Janeiro", "körte", "6015", "16", 80.0, 15.0));
			allAddress.add(new Address((long) 17, "HM", "Sao Paulo", "narancs", "6016", "17", 85.0, 10.0));
			allAddress.add(new Address((long) 18, "VA", "Mato Grosso do Sul", "barack", "6017", "18", 90.0, 80.0));
			allAddress.add(new Address((long) 19, "HN", "Paraná", "óvoda", "6018", "19", 35.0, 75.0));
			allAddress.add(new Address((long) 20, "HK", "Rio de Janeiro", "kiskörősi", "6019", "20", 40.0, 70.0));
			allAddress.add(new Address((long) 21, "HU", "Sao Paulo", "szegedi", "6020", "21", 45.0, 65.0));
			allAddress.add(new Address((long) 22, "IS", "Sao Paulo", "kőszegi", "6021", "22", 50.0, 60.0));
			allAddress.add(new Address((long) 23, "IN", "Minas Gerais", "iskola", "6022", "23", 55.0, 55.0));
			allAddress.add(new Address((long) 24, "ID", "Santa Catarina", "alma", "6023", "24", 60.0, 50.0));
			allAddress.add(new Address((long) 25, "IR", "Bahia", "körte", "6024", "25", 65.0, 45.0));
			allAddress.add(new Address((long) 26, "IQ", "Bahia", "narancs", "6025", "26", 70.0, 40.0));
			allAddress.add(new Address((long) 27, "IE", "Minas Gerais", "barack", "6026", "27", 75.0, 35.0));
			allAddress.add(new Address((long) 28, "IM", "Rio de Janeiro", "óvoda", "6027", "28", 80.0, 30.0));
			allAddress.add(new Address((long) 29, "IL", "Sao Paulo", "kiskörősi", "6028", "29", 85.0, 25.0));
			allAddress.add(new Address((long) 30, "IT", "Sao Paulo", "szegedi", "6029", "30", 90.0, 20.0));
			allAddress.add(new Address((long) 31, "JM", "Sao Paulo", "kőszegi", "6030", "31", 35.0, 15.0));
		}

		addressRepository.saveAllAndFlush(allAddress);

		List<Milestone> allMilestone = new ArrayList<>();
		{
			allMilestone.add(new Milestone((long) 1, addressRepository.findById((long) 1).get(), "2022.03.01 10:10"));
			allMilestone.add(new Milestone((long) 2, addressRepository.findById((long) 2).get(), "2022.03.02 10:20"));
			allMilestone.add(new Milestone((long) 3, addressRepository.findById((long) 3).get(), "2022.03.03 10:30"));
			allMilestone.add(new Milestone((long) 4, addressRepository.findById((long) 4).get(), "2022.03.04 10:40"));
			allMilestone.add(new Milestone((long) 5, addressRepository.findById((long) 5).get(), "2022.03.05 10:50"));
			allMilestone.add(new Milestone((long) 6, addressRepository.findById((long) 6).get(), "2022.03.01 11:00"));
			allMilestone.add(new Milestone((long) 7, addressRepository.findById((long) 7).get(), "2022.03.02 11:10"));
			allMilestone.add(new Milestone((long) 8, addressRepository.findById((long) 8).get(), "2022.03.03 11:20"));
			allMilestone.add(new Milestone((long) 9, addressRepository.findById((long) 9).get(), "2022.03.04 11:30"));
			allMilestone.add(new Milestone((long) 10, addressRepository.findById((long) 10).get(), "2022.03.05 11:40"));
			allMilestone.add(new Milestone((long) 11, addressRepository.findById((long) 11).get(), "2022.03.01 11:50"));
			allMilestone.add(new Milestone((long) 12, addressRepository.findById((long) 12).get(), "2022.03.02 12:00"));
			allMilestone.add(new Milestone((long) 13, addressRepository.findById((long) 13).get(), "2022.03.03 14:10"));
			allMilestone.add(new Milestone((long) 14, addressRepository.findById((long) 14).get(), "2022.03.04 15:10"));
			allMilestone.add(new Milestone((long) 15, addressRepository.findById((long) 15).get(), "2022.03.05 16:10"));
			allMilestone.add(new Milestone((long) 16, addressRepository.findById((long) 16).get(), "2022.03.01 17:10"));
			allMilestone.add(new Milestone((long) 17, addressRepository.findById((long) 17).get(), "2022.03.02 14:20"));
			allMilestone.add(new Milestone((long) 18, addressRepository.findById((long) 18).get(), "2022.03.03 15:20"));
			allMilestone.add(new Milestone((long) 19, addressRepository.findById((long) 19).get(), "2022.03.04 16:20"));
			allMilestone.add(new Milestone((long) 20, addressRepository.findById((long) 20).get(), "2022.03.05 17:20"));
			allMilestone.add(new Milestone((long) 21, addressRepository.findById((long) 1).get(), "2022.03.01 18:20"));
			allMilestone.add(new Milestone((long) 22, addressRepository.findById((long) 2).get(), "2022.03.02 14:20"));
			allMilestone.add(new Milestone((long) 23, addressRepository.findById((long) 3).get(), "2022.03.03 15:20"));
			allMilestone.add(new Milestone((long) 24, addressRepository.findById((long) 4).get(), "2022.03.04 16:20"));
			allMilestone.add(new Milestone((long) 25, addressRepository.findById((long) 5).get(), "2022.03.05 17:20"));
			allMilestone.add(new Milestone((long) 26, addressRepository.findById((long) 6).get(), "2022.03.01 18:20"));
			allMilestone.add(new Milestone((long) 27, addressRepository.findById((long) 7).get(), "2022.03.02 15:10"));
			allMilestone.add(new Milestone((long) 28, addressRepository.findById((long) 8).get(), "2022.03.03 16:10"));
			allMilestone.add(new Milestone((long) 29, addressRepository.findById((long) 9).get(), "2022.03.04 17:10"));
			allMilestone.add(new Milestone((long) 30, addressRepository.findById((long) 10).get(), "2022.03.05 10:10"));
			allMilestone.add(new Milestone((long) 31, addressRepository.findById((long) 11).get(), "2022.03.01 10:20"));
			allMilestone.add(new Milestone((long) 32, addressRepository.findById((long) 12).get(), "2022.03.02 10:30"));
			allMilestone.add(new Milestone((long) 33, addressRepository.findById((long) 13).get(), "2022.03.03 10:40"));
			allMilestone.add(new Milestone((long) 34, addressRepository.findById((long) 14).get(), "2022.03.04 10:50"));
			allMilestone.add(new Milestone((long) 35, addressRepository.findById((long) 15).get(), "2022.03.05 11:00"));
			allMilestone.add(new Milestone((long) 36, addressRepository.findById((long) 16).get(), "2022.03.01 11:10"));
			allMilestone.add(new Milestone((long) 37, addressRepository.findById((long) 17).get(), "2022.03.02 11:20"));
			allMilestone.add(new Milestone((long) 38, addressRepository.findById((long) 18).get(), "2022.03.03 11:30"));
			allMilestone.add(new Milestone((long) 39, addressRepository.findById((long) 19).get(), "2022.03.04 11:40"));
			allMilestone.add(new Milestone((long) 40, addressRepository.findById((long) 20).get(), "2022.03.05 11:50"));

		}

		milestoneRepository.saveAllAndFlush(allMilestone);

		List<TransportPlan> allTransportPlan = new ArrayList<>();
		{
			allTransportPlan.add(new TransportPlan((long) 1, 1000));
			allTransportPlan.add(new TransportPlan((long) 2, 2000));
			allTransportPlan.add(new TransportPlan((long) 3, 3000));
			allTransportPlan.add(new TransportPlan((long) 4, 4000));
			allTransportPlan.add(new TransportPlan((long) 5, 5000));

		}

		List<Section> allSection = new ArrayList<>();
		{
			allSection.add(new Section((long) 1, milestoneRepository.findById((long) 1).get(),
					milestoneRepository.findById((long) 21).get(), 1, allTransportPlan.get(0)));
			allSection.add(new Section((long) 2, milestoneRepository.findById((long) 2).get(),
					milestoneRepository.findById((long) 22).get(), 2, allTransportPlan.get(0)));
			allSection.add(new Section((long) 3, milestoneRepository.findById((long) 3).get(),
					milestoneRepository.findById((long) 23).get(), 3, allTransportPlan.get(0)));
			allSection.add(new Section((long) 4, milestoneRepository.findById((long) 4).get(),
					milestoneRepository.findById((long) 24).get(), 4, allTransportPlan.get(0)));
		}

		sectionRepository.saveAll(allSection);
		List<Section> allSection2 = new ArrayList<>();
		{

			allSection2.add(new Section((long) 5, milestoneRepository.findById((long) 5).get(),
					milestoneRepository.findById((long) 25).get(), 1, allTransportPlan.get(1)));
			allSection2.add(new Section((long) 6, milestoneRepository.findById((long) 6).get(),
					milestoneRepository.findById((long) 26).get(), 2, allTransportPlan.get(1)));
			allSection2.add(new Section((long) 7, milestoneRepository.findById((long) 7).get(),
					milestoneRepository.findById((long) 27).get(), 3, allTransportPlan.get(1)));
			allSection2.add(new Section((long) 8, milestoneRepository.findById((long) 8).get(),
					milestoneRepository.findById((long) 28).get(), 4, allTransportPlan.get(1)));
		}

		sectionRepository.saveAll(allSection2);

		List<Section> allSection3 = new ArrayList<>();
		{
			allSection3.add(new Section((long) 9, milestoneRepository.findById((long) 9).get(),
					milestoneRepository.findById((long) 29).get(), 1, allTransportPlan.get(2)));
			allSection3.add(new Section((long) 10, milestoneRepository.findById((long) 10).get(),
					milestoneRepository.findById((long) 30).get(), 2, allTransportPlan.get(2)));
			allSection3.add(new Section((long) 11, milestoneRepository.findById((long) 11).get(),
					milestoneRepository.findById((long) 31).get(), 3, allTransportPlan.get(2)));
			allSection3.add(new Section((long) 12, milestoneRepository.findById((long) 12).get(),
					milestoneRepository.findById((long) 32).get(), 4, allTransportPlan.get(2)));
		}

		sectionRepository.saveAll(allSection3);

		List<Section> allSection4 = new ArrayList<>();
		{
			allSection4.add(new Section((long) 13, milestoneRepository.findById((long) 13).get(),
					milestoneRepository.findById((long) 33).get(), 1, allTransportPlan.get(3)));
			allSection4.add(new Section((long) 14, milestoneRepository.findById((long) 14).get(),
					milestoneRepository.findById((long) 34).get(), 2, allTransportPlan.get(3)));
			allSection4.add(new Section((long) 15, milestoneRepository.findById((long) 15).get(),
					milestoneRepository.findById((long) 35).get(), 3, allTransportPlan.get(3)));
			allSection4.add(new Section((long) 16, milestoneRepository.findById((long) 16).get(),
					milestoneRepository.findById((long) 36).get(), 4, allTransportPlan.get(3)));
		}

		sectionRepository.saveAll(allSection4);

		List<Section> allSection5 = new ArrayList<>();
		{
			allSection5.add(new Section((long) 17, milestoneRepository.findById((long) 17).get(),
					milestoneRepository.findById((long) 37).get(), 1, allTransportPlan.get(4)));
			allSection5.add(new Section((long) 18, milestoneRepository.findById((long) 18).get(),
					milestoneRepository.findById((long) 38).get(), 2, allTransportPlan.get(4)));
			allSection5.add(new Section((long) 19, milestoneRepository.findById((long) 19).get(),
					milestoneRepository.findById((long) 39).get(), 3, allTransportPlan.get(4)));
			allSection5.add(new Section((long) 20, milestoneRepository.findById((long) 20).get(),
					milestoneRepository.findById((long) 40).get(), 4, allTransportPlan.get(4)));
		}
		sectionRepository.saveAll(allSection5);

		allTransportPlan.get(0).setSections(allSection);
		allTransportPlan.get(1).setSections(allSection2);
		allTransportPlan.get(2).setSections(allSection3);
		allTransportPlan.get(3).setSections(allSection4);
		allTransportPlan.get(4).setSections(allSection5);

		transportPlanRepository.saveAll(allTransportPlan);

	}
}
