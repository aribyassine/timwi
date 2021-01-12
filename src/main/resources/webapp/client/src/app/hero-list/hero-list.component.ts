import {Component, OnInit} from '@angular/core';
import {Hero} from "../shared/entities/Hero";
import {HeroService} from "../shared/hero/hero.service";

@Component({
  selector: 'app-hero-list',
  templateUrl: './hero-list.component.html',
  styleUrls: ['./hero-list.component.css']
})
export class HeroListComponent implements OnInit {
  heroes: Array<Hero>
  team: Set<Hero>

  constructor(private heroService: HeroService) {
  }

  ngOnInit(): void {
    this.heroService.getAll().subscribe(data => {
      this.heroes = data._embedded.heroes;
      this.team = new Set<Hero>()
      console.log(data)
    })
  }

  add(hero: Hero) {
    this.heroes.map(h => {
      if (h.id === hero.id) {
        hero.inTeam = true
      }
      return hero
    })
    this.heroService.save(hero).subscribe(data => this.team.add(data))
  }

  remove(hero: Hero) {
    this.heroes.map(h => {
      if (h.id === hero.id) {
        hero.inTeam = false
      }
      return hero
    })
    this.heroService.save(hero).subscribe(data => this.team.delete(data))
  }
}
